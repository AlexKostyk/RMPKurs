package com.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.databinding.ActivityMainBinding
import com.example.db.MainDb
import com.example.db.insertDb
import com.example.fragments.Translator
import com.example.fragments.WordBook

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = MainDb.getDb(this)
        insertDb(db)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // переключение фрагментов
        binding.buttonTranslator.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_place, Translator())
            transaction.addToBackStack(null)
            transaction.commit()
            binding.buttonWordBook.setImageResource(R.drawable.dictionary_grey)
            binding.buttonTranslator.setImageResource(R.drawable.translato_img)
        }

        binding.buttonWordBook.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_place, WordBook())
            transaction.addToBackStack(null)
            transaction.commit()
            binding.buttonTranslator.setImageResource(R.drawable.translato_img_grey)
            binding.buttonWordBook.setImageResource(R.drawable.dictionary)
        }
    }
}