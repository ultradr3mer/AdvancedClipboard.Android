package com.example.advancedclipboardandroid

import android.graphics.drawable.Drawable

object Repository {
    val items = ArrayList<ClipboardItem>()
}

data class ClipboardItem(val name: String?, val image: Drawable?) {
    override fun toString(): String {
        return name.toString()
    }
}