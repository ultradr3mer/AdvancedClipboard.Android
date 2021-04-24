package com.example.advancedclipboardandroid

import okhttp3.OkHttpClient
import okhttp3.Request
import rx.Observable
import java.io.File
import java.net.HttpURLConnection

class Downloader(val url: String, val file: File)
{
    var mime: String? = null;

    private var okHttpClient: OkHttpClient

    init {
        val okHttpBuilder = OkHttpClient.Builder()
//            .connectTimeout(HTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)
//            .readTimeout(HTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)
        this.okHttpClient = okHttpBuilder.build()
    }

    fun download(): Observable<Int> {
        return Observable.unsafeCreate<Int> { emitter ->
            val request = Request.Builder().url(url).build()
            val response = okHttpClient.newCall(request).execute()
            val body = response.body()
            mime = response.header("Content-Type")
            val responseCode = response.code()
            if (responseCode >= HttpURLConnection.HTTP_OK &&
                responseCode < HttpURLConnection.HTTP_MULT_CHOICE &&
                body != null) {
                val length = body.contentLength()
                body.byteStream().apply {
                    file.outputStream().use { fileOut ->
                        var bytesCopied = 0
                        val buffer = ByteArray(1024 * 10)
                        var bytes = read(buffer)
                        while (bytes >= 0) {
                            fileOut.write(buffer, 0, bytes)
                            bytesCopied += bytes
                            bytes = read(buffer)
                            emitter.onNext(
                                ((bytesCopied * 100)/length).toInt())
                        }
                    }
                    emitter.onCompleted()
                }
            } else {
                emitter.onError(Throwable())
                // Report the error
            }
        }
    }
}