package com.example.bigbigshopre_design.lists.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.BookCellBinding

class BookAdapter(
    private val books: List<Book>,
    private val clickListener: BookClickListener
    ) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = BookCellBinding.inflate(from, parent, false)
        return BookViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindBook(books[position])
    }

    override fun getItemCount(): Int = books.size
}