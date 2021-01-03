package com.example.advancedclipboardandroid

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView

/**
 * Component for the list items in the UI.
 */
class ListItem : MaterialCardView {

    public lateinit var item: ClipboardItem

    constructor( context: Context,  attrs: AttributeSet) : super(context, attrs) {
        this.init(attrs, 0)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        this.setOnClickListener{ _ ->
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText(item.name, item.name)
            clipboard.setPrimaryClip(clip)
        }
    }
}
