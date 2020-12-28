package com.example.advancedclipboardandroid

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_browse.*
import kotlinx.android.synthetic.main.content_browse.*

class Browse : AppCompatActivity() {

    private lateinit var clipboard: ClipboardManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        setSupportActionBar(findViewById(R.id.toolbar))
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        clipboard.addPrimaryClipChangedListener {
            val hasContent = when {
                !clipboard.hasPrimaryClip() -> {
                    false
                }
                !(clipboard.primaryClipDescription!!.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) -> {
                    // This disables the paste menu item, since the clipboard has data but it is not plain text
                    false
                }
                else -> {
                    // This enables the paste menu item, since the clipboard contains plain text.
                    true
                }
            }

            if (hasContent) {
                val item = clipboard.primaryClip?.getItemAt(0)
                Repository.items.add(ClipboardItem(item?.text.toString()))
            }
        }

//        if(clipboard.hasPrimaryClip())
//        {
//            for (i in 1..(clipboard.primaryClip!!.itemCount ?: 0))
//            {
//                val item = clipboard.primaryClip!!.getItemAt(i)
//                Repository.items.add(ClipboardItem(item.text.toString()))
//            }
//        }
//

        list.adapter = ArrayAdapter<ClipboardItem>(
            this,
            android.R.layout.simple_list_item_1,
            Repository.items
        )

        list.setOnItemClickListener { parent, view, position, id ->
            val item = Repository.items[position]
            val clip: ClipData = ClipData.newPlainText(item.name, item.name)
            clipboard.setPrimaryClip(clip)
        }

        add.isEnabled = false

        add.setOnClickListener { view ->
            // Responds to the user selecting "paste"
            // Examines the item on the clipboard. If getText() does not return null, the clip item
            // contains the text. Assumes that this application can only handle one item at a time.
            val item = clipboard.primaryClip!!.getItemAt(0)

            // Gets the clipboard as text.
            Repository.items.add(ClipboardItem(item.text.toString()))
        }
    }
//
//    private fun updateIsEnabled() {
//        add.isEnabled = when {
//            !clipboard.hasPrimaryClip() -> {
//                false
//            }
//            !(clipboard.primaryClipDescription!!.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) -> {
//                // This disables the paste menu item, since the clipboard has data but it is not plain text
//                false
//            }
//            else -> {
//                // This enables the paste menu item, since the clipboard contains plain text.
//                true
//            }
//        }
//    }
}