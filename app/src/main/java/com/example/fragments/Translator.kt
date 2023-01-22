package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.MainActivity
import com.example.databinding.FragmentTranslatorBinding
import com.example.db.MainDb


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
            var word : String?
            val translated_words = ArrayList<String>()
            editText.forEach {
                words.addAll(it.split(Regex("(?<=[!?,.;:])|(?=[!?,.;:])")))
            }
            words.forEach {
                word = findSynonym(it)
                if (word == null) {
                    translated_words.add(it)
                }
                else{
                    translated_words.add(word.toString().trim())
                }
            }
            println(translated_words)
            translated_words.forEach {
                if ((isLetters(it) && translated_words.indexOf(it) == 0) || !isLetters(it)){
                    if (words[0].toCharArray()[0].isUpperCase()){
                        translate += it.capitalize()
                    }
                    else {
                        translate += it
                    }
                }
                else{
                    translate += " $it"
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
    fun findSynonym (word: String) : String?{
        var synonym : String? = word
        val db = MainDb.getDb(context as MainActivity)
        Thread {
            synonym = db.getDao().getSynonym(word)
        }.start()
        // Сраное говно ужасное, мне стыдно... извините (это 100000000000000000% нельзя никому показывать,
        // это будет нашей тайной, ведь на ней строится все наше приложение), а еще я создал вечный двигатель
        while(synonym == word){continue}
        return synonym
    }

}