package com.example.advancedclipboardandroid

import io.swagger.client.api.ClipboardApi
import java.util.*
import kotlin.collections.ArrayList

object Repository {
    var clipboardApi: ClipboardApi? = null
    val items = ArrayList<ClipboardItem>()
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