package br.com.nglauber.books.http

import br.com.nglauber.books.model.SearchResult
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object BookHttp {
    private const val API_KEY =
        "YOUR_API_KEY"
    private const val BOOK_JSON_URL =
        "https://www.googleapis.com/books/v1/volumes?q=%s&key=$API_KEY"

    private val client = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    fun searchBook(q: String): SearchResult? {
        val request = Request.Builder()
            .url(String.format(BOOK_JSON_URL, q))
            .build()
        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson<SearchResult>(
                json, SearchResult::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}