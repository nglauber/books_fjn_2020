package br.com.nglauber.books.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.nglauber.books.R
import br.com.nglauber.books.ui.adapter.BookPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = BookPagerAdapter(this)
        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.text = if (position == 0) {
                "Livros"
            } else {
                "Favoritos"
            }
        }.attach()
    }
}
