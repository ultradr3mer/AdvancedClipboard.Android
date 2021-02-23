package com.example.advancedclipboardandroid

import io.swagger.client.ApiClient
import io.swagger.client.api.ClipboardApi
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private var verification = LoginVerification()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun loginSuccessful_whenCorrectCredentials() {
        assertTrue(verification.verify( "test@test.com",  "123"))
    }

    @Test
    fun loginFail_whenCorrectCredentials() {
        assertFalse(verification.verify( "test@tesasdt.com",  "1asd23"))
    }

    @Test
    fun getClipboardFromLocalhost()
    {
        val clipboardApi = ApiClient("http")
            .setCredentials("ClaraOriginal", "TestPassword123!")
            .createService(ClipboardApi::class.java)

        var gotItems = false;
        var gotError = false;

        clipboardApi.clipboardGet().subscribe({ mutableList ->
            mutableList.forEach { item ->
                gotItems = true;
            }
        }, { error ->
            run {
                gotError = true;
            }
        })

        assertTrue(gotItems)
        assertFalse(gotError)
    }
}