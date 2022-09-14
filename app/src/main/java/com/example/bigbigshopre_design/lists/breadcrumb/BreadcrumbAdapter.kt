package com.example.bigbigshopre_design.lists.breadcrumb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.BreaccrumbTitleCellBinding

class BreadcrumbAdapter (
    private val breadcrumbObjects: List<BreadcrumbModelClass>,
    private val clickListener: BreadcrumbClickListener
        ) : RecyclerView.Adapter<BreadcrumbAdapter.BreadcrumbViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreadcrumbViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = BreaccrumbTitleCellBinding.inflate(from,parent,false)
        return BreadcrumbViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreadcrumbViewHolder, position: Int) {
//        val listPosition = position
        val objects = breadcrumbObjects[position]
        holder.name.text = objects.name


        holder.name.setOnClickListener { clickListener.onClick(objects) }
    }

    override fun getItemCount(): Int = breadcrumbList.size

    class BreadcrumbViewHolder (
        binding: BreaccrumbTitleCellBinding
            ): RecyclerView.ViewHolder(binding.root) {
        val name = binding.name
    }
}