package com.arjun1194.myapplication.data

import com.arjun1194.myapplication.data.model.ChatListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ChatService {

    @GET("chats")
    suspend fun getChats(): Response<ChatListResponse>
}