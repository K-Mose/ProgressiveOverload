package com.example.progressiveoverload.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.progressiveoverload.data.Exercise
import com.example.progressiveoverload.databinding.ItemSelectedExerciseNameBinding

class SelectedItemAdapter(private val context: Context, private val list: List<Exercise>) : RecyclerView.Adapter<SelectedItemAdapter.MyAdapter>() {
    private lateinit var binding: ItemSelectedExerciseNameBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        binding = ItemSelectedExerciseNameBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyAdapter(binding)
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        holder.setName(list[position])
    }

    override fun getItemCount(): Int = list.size

    class MyAdapter(val binding: ItemSelectedExerciseNameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setName(exercise: Exercise) {
            binding.apply {
                tvItemName.text = exercise.name
                Log.e("${exercise.name}", "${exercise.sets}")
            }
        }
    }
}