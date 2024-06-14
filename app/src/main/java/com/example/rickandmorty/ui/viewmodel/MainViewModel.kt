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

    fun getAllCharacters() {
        viewModelScope.launch{
            _test.postValue(repository.getAllCharacters())
        }
    }
}