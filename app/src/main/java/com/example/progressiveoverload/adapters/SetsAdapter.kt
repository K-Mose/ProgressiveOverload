package com.example.progressiveoverload.adapters

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.progressiveoverload.data.Sets
import com.example.progressiveoverload.databinding.ItemSetsBinding

class SetsAdapter(val context: Context, val list: List<Sets>) : RecyclerView.Adapter<SetsAdapter.MyViewHolder>() {
    private lateinit var binding: ItemSetsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemSetsBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = list.size

    inner class MyViewHolder(binding: ItemSetsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}