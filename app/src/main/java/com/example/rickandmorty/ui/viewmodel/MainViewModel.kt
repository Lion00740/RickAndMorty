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
import kotlin.io.path.fileVisitor

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _stateList = MutableLiveData<Boolean>(true)
    val stateList:LiveData<Boolean> = _stateList

    private val _list = MutableLiveData<List<Character>>()
    val list: LiveData<List<Character>> = _list

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    private var fullList: List<Character> = listOf()
    fun getAllCharacters() {
        viewModelScope.launch{
            _error.postValue(null)
            val result = repository.getAllCharacters()

            if (result.data != null) {
                _list.postValue(result.data!!)
            }
            fullList = result.data!!
            _error.postValue(result.message)
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
            fullList = result.data!!
        }
    }
    fun setBookmark(character: Character) {
        viewModelScope.launch{
            val newList = _list.value?.map {
                if(it.id == character.id) {
                    it.copy(isBookmark = !it.isBookmark)
                } else it
            }
            _list.postValue(newList!!)
            fullList = newList
            repository.updateCharacter(character.copy(isBookmark = !character.isBookmark))
        }
    }
    fun searchCharacters(query: String) {
        val filteredList = if (query.isEmpty()) {
            fullList
        } else {
            fullList.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        _list.postValue(filteredList!!)
    }
}