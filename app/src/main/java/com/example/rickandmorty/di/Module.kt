package com.example.rickandmorty.di

import com.example.rickandmorty.data.RickAndMortyApi
import com.example.rickandmorty.data.RepositoryImpl
import com.example.rickandmorty.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideRepository(api: RickAndMortyApi) : Repository = RepositoryImpl(api)
}