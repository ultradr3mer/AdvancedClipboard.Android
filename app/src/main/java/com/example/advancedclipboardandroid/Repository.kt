package com.example.advancedclipboardandroid

import io.swagger.client.api.ClipboardApi
import io.swagger.client.model.ClipboardGetData
import java.util.*
import kotlin.collections.ArrayList

object Repository {
    var clipboardApi: ClipboardApi? = null
    val items = ArrayList<ClipboardItem>()

    fun insertNewItem(item: ClipboardGetData) {
        items.add(
            0, ClipboardItem(
                item.contentTypeId,
                item.textContent,
                FileTokenData.createUrl(item.fileContentUrl),
                item.fileName
            )
        )
    }
}

data class ClipboardItem(
    val contentTypeId: UUID,
    val name: String?,
    val fileUrl: String?,
    val fileName: String?
) {
    override fun toString(): String {
        return name.toString()
    }
}

