package com.arjun1194.myapplication.domain

import com.arjun1194.myapplication.data.model.ChatListResponse
import com.arjun1194.myapplication.data.model.ChatListResponseItem

sealed class ChatListUIState {
    data class Data(val response: ChatListResponse): ChatListUIState()
    data class Error(val throwable: Throwable?): ChatListUIState()
    data object Loading: ChatListUIState()
}

sealed class ChatDetailUIState {
    data class Data(val response: ChatListResponseItem): ChatDetailUIState()
    data class Error(val throwable: Throwable?): ChatDetailUIState()
    data object Loading: ChatDetailUIState()
}