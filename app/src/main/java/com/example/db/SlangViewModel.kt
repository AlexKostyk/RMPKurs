package com.example.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class SlangViewModel(

    val dao: Dao,
    application: Application
) : AndroidViewModel(application) {
    //Для работы с базой данных в рамках шаблона MVVM необходимо добавить ViewModel-класс с объектом DAO внутри.
    //Используются корутины для ассинхронной работы с БД
    private var viewModelJob = Job()
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    //Использование главного потока для выполнения корутин в рамках работы с ViewModel считается подходящим
    // решением, поскольку это удобно для реализации обновления UI после выполнения операций с данными.
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    init {
        startDb()
    }

    fun startDb() {
        uiScope.launch {
        }
    }


    private suspend fun insert(night: Slangtable) {
        withContext(Dispatchers.IO) {
            dao.insert(night)
        }
    }

}