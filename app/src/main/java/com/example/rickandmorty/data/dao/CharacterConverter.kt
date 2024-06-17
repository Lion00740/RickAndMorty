package com.example.rickandmorty.data.dao

import androidx.room.TypeConverter
import com.example.rickandmorty.domain.Location
import com.example.rickandmorty.domain.Origin

class CharacterConverter {
    @TypeConverter
    fun originToString(origin: Origin) : String = origin.name

    @TypeConverter
    fun stringToOrigin(str: String) : Origin = Origin(str)

    @TypeConverter
    fun locationToString(location: Location) : String = location.name

    @TypeConverter
    fun stringToLocation(str: String) : Location = Location(str)
}