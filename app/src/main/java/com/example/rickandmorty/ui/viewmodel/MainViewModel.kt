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

    private val _stateList = MutableLiveData<Boolean>(false)
    val stateList:LiveData<Boolean> = _stateList

    private val _list = MutableLiveData<List<Character>>()
    val list: LiveData<List<Character>> = _list

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error
    fun getAllCharacters() {
        viewModelScope.launch{
            _error.postValue(null)
            val result = repository.getAllCharacters()

            if (result.data != null) {
                _list.postValue(result.data!!)
                _error.postValue(result.message)
            } else {
                _error.postValue(result.message!!)
            }
        }
    }

    fun setState(bool: Boolean) {
        _stateList.postValue(bool)
    }

    fun getAllBookmarks() {
        viewModelScope.launch {
            val result = repository.getAllBookmarks()
            if (result.data != null) {
                _list.postValue(result.data!!)
            }
        }
    }
}