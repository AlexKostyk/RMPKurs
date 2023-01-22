package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.MainActivity
import com.example.databinding.FragmentWordBookBinding
import com.example.db.MainDb
import com.example.db.SlangViewModel
import com.example.db.SlangViewModelFactory


class WordBook : Fragment() {

    lateinit var binding: FragmentWordBookBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWordBookBinding.inflate(layoutInflater, container, false)

        val db = MainDb.getDb(context as MainActivity)
        var description : String?

        binding.findButton.setOnClickListener {
            val word = binding.findForm.text.toString()
            Thread{
                description = db.getDao().getDescription(word)
                if (description == null){
                    binding.descriptionText.text = "Слово не найдено"
                }
                else{
                    binding.descriptionText.text = description
                }
            }.start()
        }
        return binding.root
    }
}