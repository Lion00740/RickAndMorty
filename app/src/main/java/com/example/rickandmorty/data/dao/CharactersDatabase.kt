package com.example.rickandmorty.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.domain.Character

@Database(entities = [
    Character::class
], version = 1)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun charactersDao() : CharactersDao
}