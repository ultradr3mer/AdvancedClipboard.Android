package com.example.advancedclipboardandroid

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.text.TextUtils
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
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


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

            if (!TextUtils.isEmpty(item.name)) {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip: ClipData = ClipData.newPlainText(item.name, item.name)
                clipboard.setPrimaryClip(clip)
            }

            if (!TextUtils.isEmpty(item.imageUrl)) {
                Glide.with(this)
                    .asBitmap()
                    .load(item.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .listener(this)
                    .submit()
            }
        }
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
            bmpUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file)
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
