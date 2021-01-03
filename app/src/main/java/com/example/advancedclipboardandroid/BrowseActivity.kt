package com.example.advancedclipboardandroid

import android.content.*
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_browse.*
import kotlinx.android.synthetic.main.content_browse.*

class BrowseActivity : AppCompatActivity() {

    private lateinit var clipboard: ClipboardManager
    private lateinit var adapter: ArrayAdapter<ClipboardItem>
    val PICK_IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        setSupportActionBar(findViewById(R.id.toolbar))
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        this.adapter = ArrayAdapter<ClipboardItem>(
            this,
            android.R.layout.simple_list_item_1,
            Repository.items
        )
        list.adapter = adapter

        list.setOnItemClickListener { parent, view, position, id ->
            val item = Repository.items[position]
            val clip: ClipData = ClipData.newPlainText(item.name, item.name)
            clipboard.setPrimaryClip(clip)
            updateIsEnabled()
        }

        add.isEnabled = false

        add.setOnClickListener { view ->
            val item = clipboard.primaryClip!!.getItemAt(0)
            Repository.items.add(ClipboardItem(item.text.toString(), null))
            adapter.notifyDataSetChanged()
        }

        image.setOnClickListener { view ->
            val getIntent = Intent(Intent.ACTION_GET_CONTENT)
            getIntent.type = "image/*"

            val pickIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            pickIntent.type = "image/*"

            val chooserIntent = Intent.createChooser(getIntent, "Select Image")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

            startActivityForResult(chooserIntent, PICK_IMAGE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_browse, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_search ->
            {
                val activityIntent = Intent(this, SearchActivity::class.java)
                startActivity(activityIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateIsEnabled() {
        add.isEnabled = when {
            !clipboard.hasPrimaryClip() -> {
                false
            }
            !(clipboard.primaryClipDescription!!.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) -> {
                false
            }
            else -> {
                true
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {
            val uri = data?.data
            val stream = this.contentResolver.openInputStream(uri!!)
            var drawable = Drawable.createFromStream(stream, uri.toString())

            Repository.items.add(ClipboardItem("",drawable))
            adapter.notifyDataSetChanged()
        }
    }
}