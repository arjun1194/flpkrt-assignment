package com.arjun1194.myapplication.data

import com.arjun1194.myapplication.domain.ChatListUIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val chatService: ChatService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getChatById() {
        // TODO implement this
    }

    suspend fun getAllChats(): Flow<ChatListUIState> = flow {
        emit(ChatListUIState.Loading)
        val response = chatService.getChats()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ChatListUIState.Data(it))
            }

        } else {
            emit(ChatListUIState.Error(RuntimeException("Failed")))
        }
    }.flowOn(dispatcher)
}