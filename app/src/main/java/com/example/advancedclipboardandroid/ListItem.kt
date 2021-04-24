package com.example.advancedclipboardandroid

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.AttributeSet
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Component for the list items in the UI.
 */
class ListItem : MaterialCardView, RequestListener<Bitmap> {

    public lateinit var item: ClipboardItem

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.init(attrs, 0)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        this.setOnClickListener { _ ->

            if(item.contentTypeId == ContentTypes.PlainText)
            {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip: ClipData = ClipData.newPlainText(item.name, item.name)
                clipboard.setPrimaryClip(clip)
            }
            else if (item.contentTypeId == ContentTypes.Image)
            {
                Glide.with(this)
                    .asBitmap()
                    .load(item.fileUrl)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .listener(this)
                    .submit()
            }
            else if (item.contentTypeId == ContentTypes.File)
            {
                val file = File(
                    this.context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                    item.fileName
                )
                val downloader = Downloader(item.fileUrl!!, file)
                downloader.download()
                    .throttleFirst(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        reportStatus(it)
                    }, {
                        reportError(it)
                    }, {
                        showFile(file, downloader.mime)
                    })
            }
        }
    }

    private fun showFile(file: File, mime: String?) {
        var fileUri =  FileProvider.getUriForFile(context, context.applicationContext.packageName + ".provider", file)

        // Construct a ShareIntent with link to file
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, fileUri)
        intent.type = mime
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        // Launch sharing dialog for file
        startActivity(
            this.context,
            Intent.createChooser(
                intent,
                "Share File"
            ),
            null
        )
    }

    private fun reportError(e: Throwable?) {
        Snackbar.make(this, e!!.message!!, Snackbar.LENGTH_LONG)
            .setAction("File download", null).show()
    }

    private fun reportStatus(progress: Int?) {
        Snackbar.make(this, progress.toString() + "%", Snackbar.LENGTH_LONG)
            .setAction("File download", null).show()
    }

    fun getLocalBitmapUri(bmp: Bitmap): Uri {
        var bmpUri: Uri? = null
        try {
            val file = File(
                this.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "share_image_" + System.currentTimeMillis() + ".png"
            )
            val out = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.close()
            bmpUri = FileProvider.getUriForFile(context, context.applicationContext.packageName + ".provider", file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmpUri!!
    }

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Bitmap>?,
        isFirstResource: Boolean
    ): Boolean {
        Snackbar.make(this, e!!.message!!, Snackbar.LENGTH_LONG)
            .setAction("Paste", null).show()

        return  true;
    }

    override fun onResourceReady(
        resource: Bitmap?,
        model: Any?,
        target: Target<Bitmap>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        val bmpUri = resource?.let { getLocalBitmapUri(it) }
        if (bmpUri == null) {
            return false
        }

        // Construct a ShareIntent with link to image
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, bmpUri)
        intent.type = "image/*"
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        // Launch sharing dialog for image
        startActivity(
            this.context,
            Intent.createChooser(
                intent,
                "Share Image"
            ),
            null
        )

        return true;
    }
}
