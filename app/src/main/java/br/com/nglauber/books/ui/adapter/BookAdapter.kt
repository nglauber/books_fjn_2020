package br.com.nglauber.books.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.nglauber.books.R
import br.com.nglauber.books.model.Volume
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*

class BookAdapter(
    private val list: List<Volume>,
    private val itemCallback: (Volume) -> Unit
): RecyclerView.Adapter<BookAdapter.BookVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookVH(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BookVH, position: Int) {
        val volume = list[position]
        holder.run {
            Log.d("NGVL", volume.volumeInfo.imageLinks?.smallThumbnail ?: "vazio")
            Picasso.get()
                .load(volume.volumeInfo.imageLinks?.smallThumbnail)
                .into(imgCover)
            txtTitle.text = volume.volumeInfo.title
            txtAuthor.text = volume.volumeInfo.authors?.joinToString() ?: "Nenhum"
            txtPages.text = volume.volumeInfo.pageCount?.toString()
            itemView.setOnClickListener {
                itemCallback(volume)
            }
        }
    }

    inner class BookVH(parent: View)
        : RecyclerView.ViewHolder(parent) {
        val imgCover: ImageView = parent.imgCover
        val txtTitle: TextView = parent.txtTitle
        val txtAuthor: TextView = parent.txtAuthor
        val txtPages: TextView = parent.txtPages
    }
}