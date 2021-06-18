package com.carlos.tragosapp.domain

import com.carlos.tragosapp.data.DataSource
import com.carlos.tragosapp.data.model.Drink
import com.carlos.tragosapp.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepoImpl(private val dataSource: DataSource) : Repository {

    override  fun getDrinkList(): Flow<Resource<List<Drink>>> = flow {
        emit(dataSource.getDrinksByName())
    }


}