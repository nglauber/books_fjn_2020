package br.com.nglauber.books.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.nglauber.books.repository.BookRepository

class BookFavoritesViewModel(
    repository: BookRepository
): ViewModel() {

    val favoriteBooks = repository.allFavorites().asLiveData()
}