package br.com.nglauber.books

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.nglauber.books.repository.AppDatabase
import br.com.nglauber.books.repository.Book
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val db = AppDatabase.getDatabase(appContext)
        val dao = db.getBookDao()

        runBlocking {
            val bookUnderTest = Book("id", "http://selflink",
                "Book 1", "Desc", listOf("Eu", "tu"),
                "Novatec", "2019-01-01", 1000,
                "http://small", "http://thumb"
            )
            dao.save(bookUnderTest)
            Log.d("NGVL", "Inserted")
            val books = dao.allFavorites().first()
            books.forEach { book ->
                Log.d("NGVL", book.title)
            }
            val result = dao.delete(bookUnderTest)
            Log.d("NGVL", "Rows affected: $result")
        }
    }
}
