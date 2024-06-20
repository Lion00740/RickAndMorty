package com.example.rickandmorty.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.example.rickandmorty.domain.Character

@Dao
@TypeConverters(CharacterConverter::class)
interface CharactersDao {
    @Query("SELECT * FROM character")
    suspend fun getAllCharacters() : List<Character>

    @Query("SELECT * FROM character WHERE isBookmark = 1")
    suspend fun getAllBookmarks() : List<Character>

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacterById(id: Int) : Character
    @Insert(entity = Character::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(character: Character)

    @Update(entity = Character::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCharacter(character: Character)
}