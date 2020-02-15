package br.com.nglauber.books.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.nglauber.books.R
import br.com.nglauber.books.model.Volume
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val volume = intent.getParcelableExtra<Volume?>("book")
        volume?.run {
            txtTitle.text = volumeInfo.title
            txtAuthor.text = volumeInfo.authors?.joinToString()
            txtPages.text = volumeInfo.pageCount.toString()
            txtDescription.text = volumeInfo.description
            Picasso.get()
                .load(volumeInfo.imageLinks?.thumbnail)
                .into(imgCover)
        }

    }
}
