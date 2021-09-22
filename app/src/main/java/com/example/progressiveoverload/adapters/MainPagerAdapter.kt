package com.example.progressiveoverload.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.progressiveoverload.fragments.LogFragment
import com.example.progressiveoverload.fragments.TodayFragment

class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val count: Int,
    private val context: Context,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = count
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> LogFragment(context)
            else -> TodayFragment(context)
        }
    }
}