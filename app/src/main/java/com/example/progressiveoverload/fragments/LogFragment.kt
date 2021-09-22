package com.example.progressiveoverload.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.progressiveoverload.R
import com.example.progressiveoverload.databinding.LayoutLogBinding

class LogFragment(context: Context) : Fragment() {
    private lateinit var binding: LayoutLogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_log, container, false)
        return binding.root
    }
}