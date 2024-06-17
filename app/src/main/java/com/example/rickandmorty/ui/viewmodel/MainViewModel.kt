package com.example.rickandmorty.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.Characters
import com.example.rickandmorty.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _test = MutableLiveData<Characters>()
    val test: LiveData<Characters> = _test

    private val _error = MutableLiveData("")
    val error: LiveData<String> = _error
    fun getAllCharacters() {
        viewModelScope.launch{
            val result = repository.getAllCharacters()

            if (result.data != null) {

                _error.postValue("")
                _test.postValue(result.data!!)
            } else {
                _error.postValue(result.message!!)
            }
        }
    }
}