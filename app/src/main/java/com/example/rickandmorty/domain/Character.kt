package com.example.rickandmorty.domain
data class Character(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val image: String = "",
    val location: Location,
    val origin: Origin
)
data class Location(
    val name: String = ""
)
data class Origin(
    val name: String = ""
)

