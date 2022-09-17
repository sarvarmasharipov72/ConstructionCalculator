package com.example.constructioncalculator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.constructioncalculator.R
import com.example.constructioncalculator.databinding.ItemJobsBinding

class InsertMaterialAdapter(): RecyclerView.Adapter<InsertMaterialAdapter.MaterialViewHolder>() {
    private var list = listOf<String>()

    class MaterialViewHolder(binding: ItemJobsBinding): RecyclerView.ViewHolder(binding.root) {
        val text: TextView = itemView.findViewById(R.id.textJobMaterial)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MaterialViewHolder(ItemJobsBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        holder.text.text = list[position]
    }

    override fun getItemCount(): Int = list.size

    fun setData(el: List<String>) {
        this.list = el
        notifyDataSetChanged()
    }
}