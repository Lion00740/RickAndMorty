package com.example.rickandmorty

import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character")
    suspend fun getAllCharacters() : Characters

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int) : Character
}