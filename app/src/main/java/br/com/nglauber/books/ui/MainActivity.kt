package br.com.nglauber.books.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.nglauber.books.R
import br.com.nglauber.books.model.Volume
import br.com.nglauber.books.ui.adapter.BookAdapter
import br.com.nglauber.books.ui.viewmodel.BookListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is BookListViewModel.State.StateLoading -> {
                    progressLayout.visibility = View.VISIBLE
                }
                is BookListViewModel.State.StateLoaded -> {
                    progressLayout.visibility = View.GONE
                    val bookAdapter = BookAdapter(state.list, this@MainActivity::onVolumeClick)
                    rvBooks.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvBooks.adapter = bookAdapter
                }
                is BookListViewModel.State.StateError -> {
                    progressLayout.visibility = View.GONE
                    if (!state.hasConsumed) {
                        state.hasConsumed = true
                        Toast.makeText(
                            this@MainActivity,
                            R.string.error_load_books, Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
        viewModel.loadBooks()
    }

    private fun onVolumeClick(volume: Volume) {
        val intencao = Intent(this, BookDetailActivity::class.java)
        intencao.putExtra("book", volume)
        startActivity(intencao)
    }
}
