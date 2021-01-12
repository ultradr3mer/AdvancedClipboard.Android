package com.example.advancedclipboardandroid
import android.graphics.drawable.Drawable
import io.swagger.client.ApiClient
import io.swagger.client.api.ClipboardApi

object Repository {
    val client = ApiClient("http")

    val items = ArrayList<ClipboardItem>()

    init {
        client.setCredentials("ClaraOriginal","TestPassword123!")

        val clipboardApi = client as ClipboardApi
        clipboardApi.clipboardGet().forEach { mutableList ->
            mutableList.forEach { item ->
                this.items.add(ClipboardItem(item.plainTextContent.toString(), null))
            }
        }
    }
}

data class ClipboardItem(val name: String?, val image: Drawable?) {
    override fun toString(): String {

        return name.toString()
    }
}