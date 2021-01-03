package com.example.advancedclipboardandroid
import android.graphics.drawable.Drawable

object Repository {
    val items = ArrayList<ClipboardItem>()

    init {
        this.items.add(ClipboardItem("Test 1", null))
        this.items.add(ClipboardItem("Test 2", null))
    }
}

data class ClipboardItem(val name: String?, val image: Drawable?) {
    override fun toString(): String {
        return name.toString()
    }
}