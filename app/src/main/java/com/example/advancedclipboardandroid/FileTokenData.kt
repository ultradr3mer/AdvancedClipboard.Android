package com.example.advancedclipboardandroid

import android.text.TextUtils

object FileTokenData {

    val baseUrl = "https://advancedclipboard.azurewebsites.net/file/"

    fun createUrl(x: String?): String {
        return if (TextUtils.isEmpty(x)) "" else baseUrl + x;
    }
}