package com.a2mp.diseaseidentifier.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.a2mp.diseaseidentifier.repos.LocalRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = LocalRepository(application)

    private val _isFirstTime: MutableLiveData<Boolean> = MutableLiveData(false)
    val isFirstTime get() = _isFirstTime

    fun getIsFirstTime(): LiveData<Boolean> {

        viewModelScope.launch {

            _isFirstTime.postValue(repository.getIsFirstTime())
        }
        return isFirstTime
    }

    fun setIsFirstTime() {

        viewModelScope.launch {

            repository.setIsFirstTimeTrue()
        }
    }
}