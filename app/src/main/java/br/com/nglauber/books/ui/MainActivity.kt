package br.com.nglauber.books.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.nglauber.books.R
import br.com.nglauber.books.http.BookHttp
import br.com.nglauber.books.model.Volume
import br.com.nglauber.books.ui.adapter.BookAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                BookHttp.searchBook("Dominando o Android")
            }
            if (result != null) {
                val bookAdapter = BookAdapter(result.items, this@MainActivity::onVolumeClick)
                rvBooks.layoutManager = LinearLayoutManager(this@MainActivity)
                rvBooks.adapter = bookAdapter
            } else {
                // TODO erro
            }
        }
    }

    private fun onVolumeClick(volume: Volume) {
        val intencao = Intent(this, BookDetailActivity::class.java)
        intencao.putExtra("book", volume)
        startActivity(intencao)
    }
}
