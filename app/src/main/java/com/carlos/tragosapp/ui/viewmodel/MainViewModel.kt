package com.carlos.tragosapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.carlos.tragosapp.data.model.Drink
import com.carlos.tragosapp.domain.Repository
import com.carlos.tragosapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repository:Repository): ViewModel() {

    private val _fetchDrinksLits: MutableLiveData<Resource<List<Drink>>> = MutableLiveData()
    val fetchDrinksList: LiveData<Resource<List<Drink>>> = _fetchDrinksLits

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _fetchDrinksLits.value = Resource.Loading()
            repository.getDrinkList().collect {
            //    _fetchDrinksLits.value = Resource.Loading()
                try {
                    _fetchDrinksLits.value = it
                }catch (e:Exception){
                    _fetchDrinksLits.value = Resource.Failure(e)
                }
            }
        }
    }


}