package br.com.nglauber.books.ui.viewmodel

import androidx.lifecycle.*
import br.com.nglauber.books.http.BookHttp
import br.com.nglauber.books.model.Volume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookListViewModel: ViewModel() {

    private var query = MutableLiveData<String>()
    val state = query.switchMap {
        liveData {
            emit(State.StateLoading)
            val result = withContext(Dispatchers.IO) {
                BookHttp.searchBook(it)
            }
            emit(
                if (result?.items != null) {
                    State.StateLoaded(result.items)
                } else {
                    State.StateError(Exception("No results"), false)
                }
            )
        }
    }

    fun search(query: String) {
        this.query.value = query
    }

    sealed class State {
        object StateLoading: State()
        data class StateLoaded(val list: List<Volume>): State()
        data class StateError(val error: Throwable, var hasConsumed: Boolean): State()
    }
}