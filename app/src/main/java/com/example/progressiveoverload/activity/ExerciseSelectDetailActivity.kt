package com.example.progressiveoverload.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.progressiveoverload.R
import com.example.progressiveoverload.adapters.ExerciseDetailAdapter
import com.example.progressiveoverload.data.Exercise
import com.example.progressiveoverload.data.Sets
import com.example.progressiveoverload.databinding.ActivityExerciseSelectDetailBinding
import com.example.progressiveoverload.res.Constants

class ExerciseSelectDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseSelectDetailBinding
    private lateinit var type: String
    private lateinit var exerciseList: MutableList<Exercise>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise_select_detail)
        setContentView(binding.root)
        type = intent.getStringExtra("type").toString()
        exerciseList = (intent.getBundleExtra("list"))!!.get("list") as MutableList<Exercise>
        setActionbar()
        binding.rvExerciseDetailList.apply {
            adapter = ExerciseDetailAdapter(this@ExerciseSelectDetailActivity, Constants.excMap[type]!!, exerciseList)
                .apply {
                    setOnClickListener(object: ExerciseDetailAdapter.OnClickListener{
                        override fun itemClick(name: String) {
                            exerciseList.also {
                                var selection: Exercise? = null
                                it.forEach{ e ->
                                    if (e.name == name) selection = e
                                }
                                Log.e("e:::", "$selection")
                                if(selection == null) it.add(Exercise(name, mutableListOf()))
                                else it.remove(selection)
                            }
                        }
                    })
                }
            layoutManager = GridLayoutManager(this@ExerciseSelectDetailActivity, 2).apply {
                orientation = GridLayoutManager.VERTICAL
            }
        }

    }

    private fun setActionbar() {
        binding.toolbarExerciseDetail.apply {
            setSupportActionBar(this)
            supportActionBar!!.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_blue_back_24)
                title = type
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    override fun onBackPressed(){
        setResult(Activity.RESULT_OK, intent.putExtra("list", bundleOf("list" to exerciseList)))
        finish()
    }
}