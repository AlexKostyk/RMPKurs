package com.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.databinding.ActivityMainBinding
import com.example.db.MainDb
import com.example.db.Slangtable
import com.example.fragments.Translator
import com.example.fragments.WordBook

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDb.getDb(this)

        val slangtable = Slangtable(9998,"test",null,null)
        Thread{
            db.getDao().insertItem(slangtable)
        }.start()
        // переключение фрагментов
        binding.buttonTranslator.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_place, Translator())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.buttonWordBook.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_place, WordBook())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}