package com.example.rickandmorty.ui.viewmodel

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
class DescriptionItemViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){
    private val _test = MutableLiveData<Character>()
    val test: LiveData<Character> = _test

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(id)

            if (response.data != null) {
                _test.postValue(response.data!!)
            }
        }
    }
    fun setBookmark(id: Int) {
        viewModelScope.launch{
            _test.value?.isBookmark = _test.value?.isBookmark != true
            repository.updateCharacter(_test.value!!)
        }
    }
}