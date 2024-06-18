package com.example.rickandmorty.domain

import com.example.rickandmorty.Resource

interface Repository {
    suspend fun getAllCharacters() : Resource<List<Character>>
    suspend fun getCharacterById(id: Int) : Resource<Character>
    suspend fun getAllBookmarks() : Resource<List<Character>>
}