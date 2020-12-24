package com.example.advancedclipboardandroid

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.toolbar))

        email = findViewById(R.id.editTextTextEmailAddress)
        password = findViewById(R.id.editTextTextPassword)
        login = findViewById(R.id.button)

        login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(this, "Login", Toast.LENGTH_LONG).show()
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {

    //        }
//            else -> super.onOptionsItemSelected(item)
//            R.id.action_settings -> true
//        return when (item.itemId) {
//        // as you specify a parent activity in AndroidManifest.xml.
//        // automatically handle clicks on the Home/Up button, so long
//        // Handle action bar item clicks here. The action bar will
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//    }
//        return true
//        menuInflater.inflate(R.menu.menu_main, menu)
//        // Inflate the menu; this adds items to the action bar if it is present.

//    }
}