package com.example.progressiveoverload.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.progressiveoverload.data.Exercise
import com.example.progressiveoverload.databinding.ItemTodayExercisesBinding

class ExercisesAdapter(private val context: Context, private val list: List<Exercise> ) : RecyclerView.Adapter<ExercisesAdapter.MyViewHolder>() {
    private lateinit var binding: ItemTodayExercisesBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemTodayExercisesBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setItem(list[position])
    }

    override fun getItemCount(): Int = list.size

    class MyViewHolder(val binding: ItemTodayExercisesBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(exercise: Exercise) {
            binding.apply {
                tvName.text = exercise.name
                rvSets.apply {
                    adapter = SetsAdapter(context, exercise.sets)
                }
            }
        }
    }

}