package com.example.rickandmorty.data

import com.example.rickandmorty.Resource
import com.example.rickandmorty.data.dao.CharactersDao
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.Characters
import com.example.rickandmorty.domain.Repository
import java.lang.Exception
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val dao: CharactersDao) : Repository {
    override suspend fun getAllCharacters(): Resource<Characters> {
        return try {
            val response = rickAndMortyApi.getAllCharacters()
            val result = response.body()

            if (response.isSuccessful && result != null ) {
                result.results.forEach {
                    dao.insertCharacter(it)
                }
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "error")
        }
    }

    override suspend fun getCharacterById(id: Int): Resource<Character> {
        return try {
            val response = rickAndMortyApi.getCharacterById(id)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }

        } catch (e: Exception) {
            Resource.Error(e.message ?: "error")
        }
    }


}