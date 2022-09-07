package com.example.bigbigshopre_design.lists.book

import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.BookCellBinding

class BookViewHolder(
    private val cardCellBinding: BookCellBinding,
    private val clickListener: BookClickListener
    ): RecyclerView.ViewHolder(cardCellBinding.root) {
    fun bindBook(book: Book) {
        cardCellBinding.cover.setImageResource(book.cover)
        cardCellBinding.position.text = book.id.toString()
        cardCellBinding.title.text = book.title
        cardCellBinding.author.text = book.author

        cardCellBinding.cardView.setOnClickListener{
            clickListener.onClick(book)
        }
    }
}