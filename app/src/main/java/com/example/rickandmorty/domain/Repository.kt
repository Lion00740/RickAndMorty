package com.example.rickandmorty.domain

import com.example.rickandmorty.Resource

interface Repository {
    suspend fun getAllCharacters() : Resource<Characters>
    suspend fun getCharacterById(id: Int) : Resource<Character>
}