package com.example.advancedclipboardandroid

import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import io.swagger.client.api.ClipboardApi
import io.swagger.client.model.ClipboardPostPlainTextData
import kotlinx.android.synthetic.main.activity_browse.*
import kotlinx.android.synthetic.main.content_browse.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class BrowseActivity : AppCompatActivity() {

    private lateinit var clipboard: ClipboardManager
    private lateinit var adapter: ItemAdapter
    private lateinit var clipboardApi: ClipboardApi
    private lateinit var swipeContainer: SwipeRefreshLayout
    val PICK_IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        setSupportActionBar(findViewById(R.id.toolbar))
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        clipboardApi = Repository.clipboardApi!!

        this.adapter = ItemAdapter(
            this,
            Repository.items
        )

        recycler_view.adapter = adapter

        loadItems()

        add.setOnClickListener { view ->
            if (!this.checkIfClipboardHasText()) {
                Snackbar.make(view, "Please copy some text.", Snackbar.LENGTH_LONG)
                    .setAction("Paste", null).show()
                return@setOnClickListener
            }

            val clipboardText = clipboard.primaryClip!!.getItemAt(0).text.toString()
            val postData = ClipboardPostPlainTextData()
            postData.content = clipboardText
            clipboardApi.clipboardPostPlainTextPost(postData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false)
                .subscribe({ result ->
                    Repository.items.add(ClipboardItem(clipboardText, ""))
                    adapter.notifyDataSetChanged()
                }, { error ->
                    Snackbar.make(recycler_view, error.message.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Paste", null).show()
                })


            adapter.notifyDataSetChanged()
        }

        image.setOnClickListener { view ->
            val getIntent = Intent(Intent.ACTION_GET_CONTENT)
            getIntent.type = "image/*"

            val pickIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            pickIntent.type = "image/*"

            val chooserIntent = Intent.createChooser(getIntent, "Select Image")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

            startActivityForResult(chooserIntent, PICK_IMAGE)
        }

        swipeContainer = findViewById<View>(R.id.swipeContainer) as SwipeRefreshLayout
        swipeContainer.setOnRefreshListener({
            loadItems()
        })
    }

    private fun loadItems() {
        clipboardApi.clipboardGet()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), false)
            .subscribe({ mutableList ->
                Repository.items.clear();
                mutableList.forEach { item ->
                    Repository.items.add(0, ClipboardItem(item.textContent, item.imageContentUrl))
                }
                adapter.notifyDataSetChanged();
                swipeContainer.isRefreshing = false;
            }, { error ->
                Snackbar.make(recycler_view, error.message.toString(), Snackbar.LENGTH_LONG)
                    .setAction("Paste", null).show()
                swipeContainer.isRefreshing = false;
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_browse, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                val activityIntent = Intent(this, SearchActivity::class.java)
                startActivity(activityIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkIfClipboardHasText(): Boolean {
        return when {
            !clipboard.hasPrimaryClip() -> {
                false
            }
            !(clipboard.primaryClipDescription!!.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) -> {
                false
            }
            else -> {
                true
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {
            val uri = data?.data
            val stream = this.contentResolver.openInputStream(uri!!)
            var drawable = Drawable.createFromStream(stream, uri.toString())

//            Repository.items.add(ClipboardItem("", drawable))
            adapter.notifyDataSetChanged()
        }
    }
}