package com.example.rickandmorty.domain

interface Repository {
    suspend fun getAllCharacters() : Characters
}