package br.com.nglauber.books.repository

import android.content.Context
import br.com.nglauber.books.model.Volume
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepository(context: Context) {
    private val database = AppDatabase.getDatabase(context)

    suspend fun save(volume: Volume) {
        database.getBookDao().save(
            BookVolumeMapper.volumeToBook(volume)
        )
    }

    suspend fun delete(volume: Volume) {
        database.getBookDao().delete(
            BookVolumeMapper.volumeToBook(volume)
        )
    }

    fun allFavorites(): Flow<List<Volume>> {
        return database.getBookDao()
            .allFavorites()
            .map { bookList ->
                bookList.map { book ->
                    BookVolumeMapper.bookToVolume(book)
                }
            }
    }
}