package br.com.nglauber.books.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
@TypeConverters(StringListConverter::class)
data class Book(
    @PrimaryKey
    val id: String,
    val selfLink: String,
    val title: String,
    val description: String?,
    val authors: List<String>?,
    val publisher: String?,
    val publishedDate: String?,
    val pageCount: Int?,
    val smallThumbnail: String,
    val thumbnail: String
)