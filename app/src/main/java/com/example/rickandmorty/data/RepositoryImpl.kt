package com.example.rickandmorty.data

import com.example.rickandmorty.RickAndMortyApi
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.Characters
import com.example.rickandmorty.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val rickAndMortyApi: RickAndMortyApi) : Repository {
    override suspend fun getAllCharacters(): Characters {
        val response = rickAndMortyApi.getAllCharacters()

        return response.body()!!

    }
}