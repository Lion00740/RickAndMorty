package com.example.rickandmorty

import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.Characters
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character")
    suspend fun getAllCharacters() : Response<Characters>
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int) : Call<Character>
}