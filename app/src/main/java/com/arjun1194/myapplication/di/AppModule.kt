package com.arjun1194.myapplication.di

import com.arjun1194.myapplication.data.ChatRepository
import com.arjun1194.myapplication.data.ChatService
import com.arjun1194.myapplication.domain.Constants.END_POINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideChatClient(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideChatService(retrofit: Retrofit): ChatService =
        retrofit.create(ChatService::class.java)

    @Provides
    @Singleton
    fun provideChatRepository(chatService: ChatService): ChatRepository =
        ChatRepository(chatService)


}