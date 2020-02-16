package br.com.nglauber.books.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.nglauber.books.R
import br.com.nglauber.books.model.Volume
import br.com.nglauber.books.repository.BookRepository
import br.com.nglauber.books.ui.BookDetailActivity
import br.com.nglauber.books.ui.adapter.BookAdapter
import br.com.nglauber.books.ui.viewmodel.BookFavoritesViewModel
import br.com.nglauber.books.ui.viewmodel.BookVmFactory
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookFavoritesFragment: Fragment() {

    private val viewModel: BookFavoritesViewModel by lazy {
        ViewModelProvider(
            this,
            BookVmFactory(
                BookRepository(requireContext())
            )
        ).get(BookFavoritesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(
            R.layout.fragment_book_list,
            container,
            false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView.visibility = View.GONE
        viewModel.favoriteBooks.observe(
            viewLifecycleOwner,
            Observer { volumeList ->
                val bookAdapter = BookAdapter(
                    volumeList, this@BookFavoritesFragment::onVolumeClick
                )
                rvBooks.layoutManager = LinearLayoutManager(requireContext())
                rvBooks.adapter = bookAdapter
            }
        )
    }

    private fun onVolumeClick(volume: Volume) {
        BookDetailActivity.openWithVolume(requireContext(), volume)
    }
}