package com.example.advancedclipboardandroid

class LoginVerification {
    fun verify(email: String, password: String) : Boolean {
        return  email == "test@test.com" && password == "123"
    }
}