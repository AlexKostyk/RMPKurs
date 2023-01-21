package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.databinding.FragmentWordBookBinding


class WordBook : Fragment() {

    lateinit var binding: FragmentWordBookBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordBookBinding.inflate(layoutInflater, container, false)
        binding.findButton.setOnClickListener {
            val word = binding.findForm.text
        }
        return binding.root
    }
}