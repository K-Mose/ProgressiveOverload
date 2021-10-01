package com.example.progressiveoverload.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.progressiveoverload.data.Exercise
import com.example.progressiveoverload.databinding.ItemExerciseDetailBinding

class ExerciseDetailAdapter(val context: Context, val list: List<String>, val eList: List<Exercise>) : RecyclerView.Adapter<ExerciseDetailAdapter.MyViewHolder>(){
    private lateinit var binding: ItemExerciseDetailBinding
    private var onClickListener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemExerciseDetailBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var s = false
        Log.e("eList", "$eList")
        eList?.onEach {
            if(list[position] == it.name) s = true
        }
        Log.e("${list[position]}", "$s")
        holder.setName(list[position], onClickListener!!, s)
    }

    override fun getItemCount(): Int = list.size
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    interface OnClickListener {
        fun itemClick(name: String)
    }

    class MyViewHolder(private val binding: ItemExerciseDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        private var selected = false
        fun setName(name: String, oCl: OnClickListener, _selected: Boolean) {
            selected = _selected
            isSelected()
            binding.apply {
                tvExerciseName.text = name
                cl.setOnClickListener {
                    isSelected()
                    Log.e("click","ed : $selected")
                    oCl.itemClick(name)
                }
            }
        }
        private fun isSelected() {
            binding.apply{
                if (selected) {
                    ivSelected.visibility = View.VISIBLE
                    selected = false
                } else {
                    ivSelected.visibility = View.GONE
                    selected = true
                }
            }
        }
    }
}