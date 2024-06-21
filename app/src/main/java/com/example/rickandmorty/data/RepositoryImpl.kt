package com.example.rickandmorty.data

import com.example.rickandmorty.util.Resource
import com.example.rickandmorty.data.dao.CharactersDao
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.Repository
import java.lang.Exception
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val dao: CharactersDao) : Repository {
    override suspend fun getAllCharacters(): Resource<List<Character>> {
        return try {
            val response = rickAndMortyApi.getAllCharacters()
            val result = response.body()

            if (response.isSuccessful && result != null ) {
                result.results.forEach {
                    dao.insertCharacter(it)
                }
                Resource.Success(dao.getAllCharacters())
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.WithoutNet(dao.getAllCharacters(), e.message ?: "error")
        }
    }
    override suspend fun getCharacterById(id: Int): Resource<Character> {
        return Resource.Success(dao.getCharacterById(id))
    }
    override suspend fun getAllBookmarks(): Resource<List<Character>> {
        return try {
            Resource.Success(dao.getAllBookmarks())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "error")
        }
    }
    override suspend fun updateCharacter(character: Character) {
        dao.updateCharacter(character)
    }
}