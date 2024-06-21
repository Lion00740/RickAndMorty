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
    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(id)

            if (response.data != null) {
                _character.postValue(response.data!!)
            }
        }
    }
    fun setBookmark() {
        viewModelScope.launch{
            _character.value?.isBookmark = _character.value?.isBookmark != true
            repository.updateCharacter(_character.value!!)
        }
    }
}