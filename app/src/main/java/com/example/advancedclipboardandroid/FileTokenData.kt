package com.example.advancedclipboardandroid

object FileTokenData {

    val baseUrl = "https://advancedclipboard.azurewebsites.net/file/"

    fun createUrl(x: String): String {
        return baseUrl + x;
    }
}