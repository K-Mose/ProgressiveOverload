package com.example.progressiveoverload.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.progressiveoverload.R
import com.example.progressiveoverload.adapters.MainPagerAdapter
import com.example.progressiveoverload.data.Exercise
import com.example.progressiveoverload.databinding.ActivityMainBinding
import com.example.progressiveoverload.viewmodel.ExerciseViewModel
import com.example.progressiveoverload.viewmodel.ExerciseViewModelFactory
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        binding.apply {
            tlMain.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    vpMain.currentItem = tab!!.position // change Position
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            vpMain.apply {
                adapter = MainPagerAdapter(supportFragmentManager, tlMain.tabCount, this@MainActivity, lifecycle)
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        tlMain.selectTab(tlMain.getTabAt(position)) // change Position
                    }
                })
            }
        }
    }
    companion object {
        var listExercise: List<Exercise> = listOf()
    }
}