package com.example.bigbigshopre_design

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.CardCellBinding

class CardAdapter(private val books: List<Book>) : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val bidng = CardCellBinding.inflate(from, parent, false)
        return CardViewHolder(bidng)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindBook(books[position])
    }

    override fun getItemCount(): Int = books.size
}