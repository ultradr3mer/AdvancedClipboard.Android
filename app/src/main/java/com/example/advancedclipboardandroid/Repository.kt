package com.example.advancedclipboardandroid

import android.graphics.drawable.Drawable
import io.swagger.client.api.ClipboardApi

object Repository {
    var clipboardApi: ClipboardApi? = null
    val items = ArrayList<ClipboardItem>()
}

data class ClipboardItem(val name: String?, val image: Drawable?) {
    override fun toString(): String {
        return name.toString()
    }
}