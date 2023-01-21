package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.databinding.FragmentTranslatorBinding



class Translator : Fragment() {

    lateinit var binding: FragmentTranslatorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTranslatorBinding.inflate(layoutInflater, container, false)
        binding.translationButton.setOnClickListener {
            // получение предложения из translator и его дальнейшая обработка и форматирование
            val editText = stringToWords(binding.editTranslation.text.toString())
            val textView = binding.translationText
            var translate = ""
            val words = ArrayList<String>()
            editText.forEach { i ->
                words.addAll(i.split(Regex("(?<=[!?,.;:])|(?=[!?,.;:])")))
            }
            words.forEach { i ->
                if ((isLetters(i) && words.indexOf(i) == 0) || !isLetters(i)){
                    translate += i
                }
                else{
                    translate += " $i"
                }
            }
            textView.text = translate
        }
        return binding.root
    }
    fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotBlank() }
        .toList()
    fun isLetters(string: String): Boolean {
        return string.all { it.isLetter() }
    }

}