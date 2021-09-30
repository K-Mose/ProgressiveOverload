package com.example.progressiveoverload.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.progressiveoverload.databinding.ItemSelectExercisesBinding

class SelectExerciseAdapter(private val context: Context, private val list: List<String>) : RecyclerView.Adapter<SelectExerciseAdapter.MyAdapter>() {
    private lateinit var binding: ItemSelectExercisesBinding
    private var onClickListener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        binding = ItemSelectExercisesBinding.inflate(LayoutInflater.from(context))
        return MyAdapter(binding)
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        holder.setName(list[position])
        holder.itemClick(onClickListener, list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    interface OnClickListener {
        fun onClick(name: String)
    }

    class MyAdapter(private val binding: ItemSelectExercisesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun itemClick(onClickListener: OnClickListener?, exercise: String) {
            binding.llExerciseType.setOnClickListener {
                onClickListener?.onClick(exercise)
            }
        }
        fun setName(exercise: String) {
            Log.e("TAGS", "$exercise")
            binding.apply {
                tvName.text = exercise
            }
        }
    }
}