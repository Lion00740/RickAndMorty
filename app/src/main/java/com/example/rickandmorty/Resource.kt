package com.example.rickandmorty

sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)
    class WithoutNet<T>(data: T, message: String?) : Resource<T>(data, message)
}