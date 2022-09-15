package com.example.bigbigshopre_design.lists.breadcrumb

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.BreaccrumbTitleCellBinding

class BreadcrumbAdapter (
    private val breadcrumbObjects: MutableList<BreadcrumbModelClass>,
    private val clickListener: BreadcrumbClickListener
        ) : RecyclerView.Adapter<BreadcrumbAdapter.BreadcrumbViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreadcrumbViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = BreaccrumbTitleCellBinding.inflate(from, parent,false)
        return BreadcrumbViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreadcrumbViewHolder, position: Int) {
//        val listPosition = position
        val objects = breadcrumbObjects[position]

        if (position == 0) {
            holder.name.setCompoundDrawablesWithIntrinsicBounds( 0,0,0,0)
        }

        holder.name.text = objects.name

        holder.name.setOnClickListener { clickListener.onClick(objects) }
    }

    override fun getItemCount(): Int = breadcrumbObjects.size

    class BreadcrumbViewHolder (
        binding: BreaccrumbTitleCellBinding
            ): RecyclerView.ViewHolder(binding.root) {
        var name = binding.name
        val img = binding.name.drawableState
    }
}