package com.example.advancedclipboardandroid

object Repository {
    val items = ArrayList<ClipboardItem>()

    init {
        this.items.add(ClipboardItem("Test 1"))
        this.items.add(ClipboardItem("Test 2"))
    }
}

data class ClipboardItem( val name: String) {
    override fun toString(): String {
        return name
    }
}