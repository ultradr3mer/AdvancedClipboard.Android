package com.example.advancedclipboardandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import io.swagger.client.model.ClipboardPostPlainTextData
import kotlinx.android.synthetic.main.content_text_input.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class TextInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_input)
        setSupportActionBar(findViewById(R.id.toolbar))

        val clipboardApi = Repository.clipboardApi!!

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val postData = ClipboardPostPlainTextData()
            postData.content = text.text.toString()
            clipboardApi.clipboardPostPlainTextPost(postData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false)
                .subscribe({ result ->
                    Repository.insertNewItem(result)
                    val activityIntent = Intent(this, BrowseActivity::class.java)
                    startActivity(activityIntent)
                }, { error ->
                    Snackbar.make(text, error.message.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Post", null).show()
                })
        }
    }
}