package com.example.progressiveoverload.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.progressiveoverload.R
import com.example.progressiveoverload.adapters.SelectExerciseAdapter
import com.example.progressiveoverload.adapters.SelectedItemAdapter
import com.example.progressiveoverload.data.Exercise
import com.example.progressiveoverload.data.Sets
import com.example.progressiveoverload.databinding.ActivityExerciseSelectBinding

class ExerciseSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseSelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise_select)
        setContentView(binding.root)

        binding.apply {
            rvSelectedExercise.adapter = SelectedItemAdapter(this@ExerciseSelectActivity, listOf(
                Exercise("운동1", listOf(Sets(0.0,0))),
                Exercise("운동2", listOf(Sets(0.0,0))),
                Exercise("운동3", listOf(Sets(0.0,0))),
                Exercise("운동3", listOf(Sets(0.0,0))),
                Exercise("운동3", listOf(Sets(0.0,0))),
                Exercise("운동3", listOf(Sets(0.0,0))),
                Exercise("운동3", listOf(Sets(0.0,0))),
                Exercise("운동3", listOf(Sets(0.0,0)))
            ))
            rvSelectedExercise.layoutManager = LinearLayoutManager(this@ExerciseSelectActivity).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            val exerciseList = listOf(
            resources.getString(R.string.back),
            resources.getString(R.string.chest),
            resources.getString(R.string.shoulder),
            resources.getString(R.string.arm),
            resources.getString(R.string.leg))
            rvSelectExerciseList.apply {
                adapter = SelectExerciseAdapter(this@ExerciseSelectActivity, exerciseList)
                layoutManager = LinearLayoutManager(this@ExerciseSelectActivity)
            }

        }
    }
}