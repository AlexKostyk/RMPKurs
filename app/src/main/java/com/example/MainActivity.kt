package com.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.databinding.ActivityMainBinding
import com.example.db.MainDb
import com.example.db.SlangViewModel
import com.example.db.SlangViewModelFactory
import com.example.db.Slangtable
import com.example.fragments.Translator
import com.example.fragments.WordBook

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = requireNotNull(this).application
        val dao = MainDb.getDb(application).getDao()
        val viewModelFactory = SlangViewModelFactory(dao, application)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SlangViewModel::class.java)
        viewModel.startDb()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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