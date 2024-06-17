package com.example.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.RickAndMortyApi
import com.example.rickandmorty.data.RepositoryImpl
import com.example.rickandmorty.data.dao.CharactersDatabase
import com.example.rickandmorty.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://rickandmortyapi.com/api/"

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideRickAndMortyApi() : RickAndMortyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RickAndMortyApi::class.java)
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        CharactersDatabase::class.java,
        "room_database"
    ).build()
    @Provides
    @Singleton
    fun provideRepository(api: RickAndMortyApi, database: CharactersDatabase) : Repository = RepositoryImpl(api, database.charactersDao())
}