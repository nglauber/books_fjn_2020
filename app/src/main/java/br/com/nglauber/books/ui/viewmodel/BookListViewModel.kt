package br.com.nglauber.books.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nglauber.books.http.BookHttp
import br.com.nglauber.books.model.Volume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookListViewModel: ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun loadBooks() {
        if (state.value != null) return

        search("Dominando o Android")
    }

    fun search(query: String) {
        viewModelScope.launch {
            _state.value = State.StateLoading
            val result = withContext(Dispatchers.IO) {
                BookHttp.searchBook(query)
            }
            if (result?.items != null) {
                _state.value = State.StateLoaded(result.items)
            } else {
                _state.value = State.StateError(Exception("No results"), false)
            }
        }
    }

    sealed class State {
        object StateLoading: State()
        data class StateLoaded(val list: List<Volume>): State()
        data class StateError(val error: Throwable, var hasConsumed: Boolean): State()
    }
}