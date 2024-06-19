package com.dicoding.picodiploma.loginwithanimation.data.remote.book

import com.google.gson.annotations.SerializedName

data class SavedBookResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Book(

	@field:SerializedName("ISBN")
	val iSBN: String? = null,

	@field:SerializedName("Year-Of-Publication")
	val yearOfPublication: String? = null,

	@field:SerializedName("Image-URL-S")
	val imageURLS: String? = null,

	@field:SerializedName("Book-Title")
	val bookTitle: String? = null,

	@field:SerializedName("Image-URL-L")
	val imageURLL: String? = null,

	@field:SerializedName("Image-URL-M")
	val imageURLM: String? = null,

	@field:SerializedName("Publisher")
	val publisher: String? = null,

	@field:SerializedName("Book-Author")
	val bookAuthor: String? = null
)

data class Data(

	@field:SerializedName("books")
	val books: List<BooksItem?>? = null,

	@field:SerializedName("lastCreatedAt")
	val lastCreatedAt: String? = null
)

data class BooksItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("book")
	val book: Book? = null,

	@field:SerializedName("id")
	val id: String? = null
)
