package com.example.playwithcompose.di

import com.example.playwithcompose.data.networking.services.TodoService
import com.example.playwithcompose.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApi(): TodoService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com") // TODO: Move this to the networking layer
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(todoApi: TodoService): Repository {
        return Repository(todoApi)
    }
}
