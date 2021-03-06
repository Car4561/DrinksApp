package com.carlos.tragosapp.vo

import java.lang.Exception

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Sucess<out T>(val data: T): Resource<T>()
    data class Failure<out T>(val exception:Exception): Resource<T>()
    class Empty<out T> : Resource<T>()
}
