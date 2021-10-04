package com.example.progressiveoverload.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.progressiveoverload.R
import com.example.progressiveoverload.adapters.ExerciseDetailAdapter
import com.example.progressiveoverload.adapters.SelectExerciseAdapter
import com.example.progressiveoverload.adapters.SelectedItemAdapter
import com.example.progressiveoverload.data.Exercise
import com.example.progressiveoverload.data.Sets
import com.example.progressiveoverload.databinding.ActivityExerciseSelectBinding
import com.example.progressiveoverload.viewmodel.ExerciseViewModel
import com.example.progressiveoverload.viewmodel.ExerciseViewModelFactory

class ExerciseSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseSelectBinding
    private lateinit var eList: List<Exercise>
    private val selectResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            eList = (it.data!!.getBundleExtra("list"))!!.get("list") as List<Exercise>
            Log.e("getList:", "$eList")
            binding.rvSelectedExercise.adapter = SelectedItemAdapter(this@ExerciseSelectActivity, eList)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise_select)
        setContentView(binding.root)
        setupActionbar()

        binding.apply {
            eList = (intent.getBundleExtra("list"))!!.get("list") as List<Exercise>
            rvSelectedExercise.adapter = SelectedItemAdapter(this@ExerciseSelectActivity, eList)
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
                adapter = SelectExerciseAdapter(this@ExerciseSelectActivity, exerciseList).apply {
                    setOnClickListener(object : SelectExerciseAdapter.OnClickListener{
                        override fun onClick(name: String) {
                            selectResult.launch(
                                Intent(this@ExerciseSelectActivity, ExerciseSelectDetailActivity::class.java)
                                    .putExtra("type", name)
                                    .putExtra("list", bundleOf("list" to eList))
                            )
                            //startActivity(Intent(this@ExerciseSelectActivity, ExerciseSelectDetailActivity::class.java).putExtra("type", name))
                        }
                    })
                }
                layoutManager = LinearLayoutManager(this@ExerciseSelectActivity)
            }

        }
    }

    private fun setupActionbar() {
        binding.toolbarProfileActivity.apply {
            setSupportActionBar(this)
            val actionbar = supportActionBar.also {
                it!!.setDisplayHomeAsUpEnabled(true)
                it!!.setHomeAsUpIndicator(R.drawable.ic_blue_back_24)
                it!!.title = "" //resources.getString(R.string.today_exercise)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_OK, intent.putExtra("list", bundleOf("list" to eList)))
        finish()
    }
}