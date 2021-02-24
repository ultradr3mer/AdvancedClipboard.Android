package com.example.advancedclipboardandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.swagger.client.ApiClient
import io.swagger.client.api.ClipboardApi
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    private val loginVerification = LoginVerification()

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.editTextTextUsername)
        password = findViewById(R.id.editTextTextPassword)
        login = findViewById(R.id.button)

        login.setOnClickListener(this)

        val sp = getSharedPreferences("Login", Context.MODE_PRIVATE)
        sharedPreferencesEditor = sp.edit()

        username.setText(sp.getString(getString(R.string.User), ""))
        password.setText(sp.getString(getString(R.string.Pass), ""))

        if(username.text.any()) {
            TryLogin()
        }
    }

    override fun onClick(v: View?) {
        TryLogin()
    }

    private fun TryLogin() {
        val api = ApiClient("http")
            .setCredentials(username.text.toString(), password.text.toString())
            .createService(ClipboardApi::class.java)

        api.clipboardAuthorizeGet()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), false)
            .subscribe({ _ ->
                sharedPreferencesEditor.putString(
                    getString(R.string.User),
                    username.text.toString()
                )
                sharedPreferencesEditor.putString(
                    getString(R.string.Pass),
                    password.text.toString()
                )
                sharedPreferencesEditor.commit()
                Repository.clipboardApi = api
                val activityIntent = Intent(this, BrowseActivity::class.java)
                startActivity(activityIntent)
            }, { error ->
                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
            })
    }
}