package com.example.progressiveoverload.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.progressiveoverload.R
import com.example.progressiveoverload.activity.ExerciseSelectActivity
import com.example.progressiveoverload.adapters.ExercisesAdapter
import com.example.progressiveoverload.data.Exercise
import com.example.progressiveoverload.databinding.LayoutToadyExerciseBinding
import com.example.progressiveoverload.viewmodel.ExerciseViewModel
import com.example.progressiveoverload.viewmodel.ExerciseViewModelFactory

class TodayFragment(private val mainContext: Context) : Fragment() {
    private lateinit var binding: LayoutToadyExerciseBinding

    private lateinit var eViewModel: ExerciseViewModel
    private lateinit var eViewModelFactory: ExerciseViewModelFactory

    private val exerciseSelectIntent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            Log.e("getList:","${(it.data!!.getBundleExtra("list"))!!.get("list") as MutableList<Exercise>}")
            eViewModel.setList((it.data!!.getBundleExtra("list"))!!.get("list") as MutableList<Exercise>)
            Log.e("isEmpty","${eViewModel._exerciseList.value!!.isEmpty()}")
            setView()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_toady_exercise, container, false)
        eViewModelFactory = ExerciseViewModelFactory()
        eViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setView()
    }

    private fun setView() {
        binding.apply {
            var eList = eViewModel._exerciseList.value!!
            if(eViewModel._exerciseList.value!!.isEmpty()) {
                llToday.visibility = View.VISIBLE
                rvExerciseList.visibility = View.GONE
                ivAdd.setOnClickListener {
                    exerciseSelectIntent.launch(
                        Intent(activity, ExerciseSelectActivity::class.java)
                            .putExtra("list", bundleOf("list" to eList))
                    )
                }
            } else {
                llToday.visibility = View.GONE
                rvExerciseList.apply {
                    visibility = View.VISIBLE
                    layoutManager = LinearLayoutManager(mainContext)
                    adapter = ExercisesAdapter(context, eViewModel._exerciseList.value!!)
                }
            }
        }
    }
}