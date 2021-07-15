package com.carlos.tragosapp.presentation

import androidx.lifecycle.*
import com.carlos.tragosapp.domain.models.Drink
import com.carlos.tragosapp.domain.models.DrinkEntity
import com.carlos.tragosapp.domain.Repository
import com.carlos.tragosapp.vo.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repository: Repository) : ViewModel() {

//    private val _fetchDrinksLits: MutableLiveData<Resource<List<Drink>>> = MutableLiveData()
//    val fetchDrinksList: LiveData<Resource<List<Drink>>> = _fetchDrinksLits

    private val _fetchDrinksLits: MutableStateFlow<Resource<List<Drink>>> = MutableStateFlow(Resource.Empty())
    val fetchDrinksList: StateFlow<Resource<List<Drink>>> = _fetchDrinksLits

    private val _fetchDrinksFavoriteLits: MutableStateFlow<Resource<List<Drink>>> = MutableStateFlow(Resource.Empty())
    val fetchDrinksFavoriteLits: StateFlow<Resource<List<Drink>>> = _fetchDrinksFavoriteLits

    init {
       getDrinks()
       getDrinksFavorite()
    }

    fun getDrinks(drinkName : String = "margarita"){
        viewModelScope.launch {
            _fetchDrinksLits.value = Resource.Loading()
            repository.getDrinksList(drinkName = drinkName)
                .onEach {
                    _fetchDrinksLits.value = Resource.Sucess(it)
                }.catch { cause: Throwable ->
                    _fetchDrinksLits.value = Resource.Failure(Exception(cause))
                }.launchIn(this)
        }
    }

    fun getDrinksFavorite(){
        viewModelScope.launch {
            _fetchDrinksFavoriteLits.value = Resource.Loading()
            repository.getDrinksFavorites()
                .onEach {
                    _fetchDrinksFavoriteLits.value = Resource.Sucess(it)
                }.catch { cause: Throwable ->
                    _fetchDrinksFavoriteLits.value = Resource.Failure(Exception(cause))
                }.launchIn(this)
        }
    }


    fun insertDrink(drink: Drink){
        viewModelScope.launch {
            repository.insertDrink(drink)
        }
    }

    fun deleteDrink(drink: Drink) {
        viewModelScope.launch {
            repository.deleteDrink(drink)
        }
    }


}