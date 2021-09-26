package com.example.progressiveoverload.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.progressiveoverload.databinding.ItemSelectExercisesBinding

class SelectExerciseAdapter(private val context: Context, private val list: List<String>) : RecyclerView.Adapter<SelectExerciseAdapter.MyAdapter>() {
    private lateinit var binding: ItemSelectExercisesBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        binding = ItemSelectExercisesBinding.inflate(LayoutInflater.from(context))
        return MyAdapter(binding)
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        holder.setName(list[position])
    }

    override fun getItemCount(): Int = list.size

    class MyAdapter(private val binding: ItemSelectExercisesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setName(exercise: String) {
            Log.e("TAGS", "$exercise")
            binding.apply {
                tvName.text = exercise
            }
        }
    }
}