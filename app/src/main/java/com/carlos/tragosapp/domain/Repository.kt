package com.carlos.tragosapp.domain

import com.carlos.tragosapp.data.model.Drink
import com.carlos.tragosapp.vo.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getDrinkList(): Flow<Resource<List<Drink>>>
}