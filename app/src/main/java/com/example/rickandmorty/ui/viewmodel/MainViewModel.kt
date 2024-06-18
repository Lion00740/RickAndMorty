package com.example.rickandmorty.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _test = MutableLiveData<List<Character>>()
    val test: LiveData<List<Character>> = _test

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error
    fun getAllCharacters() {
        viewModelScope.launch{
            _error.postValue(null)
            val result = repository.getAllCharacters()

            if (result.data != null) {
                _test.postValue(result.data!!)
                _error.postValue(result.message)
            } else {
                _error.postValue(result.message!!)
            }
        }
    }

    fun getAllBookmarks() {
        viewModelScope.launch {
            val result = repository.getAllBookmarks()
            if (result.data != null) {
                _test.postValue(result.data!!)
            }
        }
    }
}