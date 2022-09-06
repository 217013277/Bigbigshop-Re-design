package com.example.bigbigshopre_design

import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.CardCellBinding

class CardViewHolder(
    private val cardCellBinding: CardCellBinding,
    private val clickListener: BookClickListener
    ): RecyclerView.ViewHolder(cardCellBinding.root) {
    fun bindBook(book: Book) {
        cardCellBinding.cover.setImageResource(book.cover)
        cardCellBinding.title.text = book.title
        cardCellBinding.author.text = book.author

        cardCellBinding.cardView.setOnClickListener{
            clickListener.onClick(book)
        }
    }
}