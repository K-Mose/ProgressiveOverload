package com.example.progressiveoverload.adapters

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowMetrics
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progressiveoverload.data.Exercise
import com.example.progressiveoverload.data.Sets
import com.example.progressiveoverload.databinding.DialogSetsBinding
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
        private val dialogBiding = DialogSetsBinding.inflate(LayoutInflater.from(context))
        private lateinit var _exercise: Exercise
        private lateinit var exerciseAdapter: SetsAdapter
        fun setItem(exercise: Exercise) {
            _exercise = exercise
            val dialog = setsDialog()
            binding.apply {
                tvName.text = exercise.name
                rvSets.apply {
                    exerciseAdapter = SetsAdapter(context, exercise.sets)
                    adapter = exerciseAdapter
                    layoutManager = LinearLayoutManager(context).apply {
                        orientation = LinearLayoutManager.HORIZONTAL
                    }
                }

                llExerciseList.setOnClickListener {
                    var width = context.resources.displayMetrics.widthPixels  * 0.8
                    dialog.window!!.setLayout(width.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
                    dialogBiding.tvDialogName.text = exercise.name
                    dialog.show()

                }
            }
        }
        private fun checkValue(kg: Double, n:Int) : Boolean {
            return kg > 0 && n > 0
        }

        private fun updateSets(sets:Sets) {
            _exercise.sets.add(sets)
            SetsAdapter(context, _exercise.sets)
            exerciseAdapter!!.notifyDataSetChanged()
        }

        private fun setsDialog(): Dialog {
            return Dialog(context).apply {
                dialogBiding.apply {
                    setContentView(this.root)
                    btnCancel.setOnClickListener {
                        dismiss()
                    }
                    btnSave.setOnClickListener {
                        val weight = etWeight.text.toString().toDouble()
                        val number = etNumber.text.toString().toInt()
                        if(checkValue(weight, number)) {
                            updateSets(Sets(weight, number))
                            dismiss()
                        }
                    }
                }
                create()
            }
        }
    }
}