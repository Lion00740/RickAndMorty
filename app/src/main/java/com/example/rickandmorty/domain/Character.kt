package com.example.rickandmorty.domain
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.data.dao.CharacterConverter

@TypeConverters(CharacterConverter::class)
@Entity(tableName = "character")
data class Character(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "status")
    val status: String = "",
    @ColumnInfo(name = "species")
    val species: String = "",
    @ColumnInfo(name = "imageUrl")
    val image: String = "",
    @ColumnInfo(name = "location")
    val location: Location,
    @ColumnInfo(name = "origin")
    val origin: Origin,
    @ColumnInfo(name = "isBookmark")
    val isBookmark: Boolean = false
)
data class Location(
    val name: String = ""
)
data class Origin(
    val name: String = ""
)

