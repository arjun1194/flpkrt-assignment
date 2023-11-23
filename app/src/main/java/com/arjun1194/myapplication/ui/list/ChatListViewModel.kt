package com.arjun1194.myapplication.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arjun1194.myapplication.data.ChatRepository
import com.arjun1194.myapplication.data.model.ChatListResponse
import com.arjun1194.myapplication.data.model.ChatListResponseItem
import com.arjun1194.myapplication.data.model.Message
import com.arjun1194.myapplication.domain.ChatDetailUIState
import com.arjun1194.myapplication.domain.ChatListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {


    // TODO: fields could be private and expose a readonly state for a safer access
     val isLoading = MutableStateFlow(false)
     val errorState = MutableStateFlow<String?>(null)
     val state = MutableStateFlow<ChatListResponse?>(null)


    suspend fun fetchChatList() {
        chatRepository.getAllChats().onEach {
            when(it) {
                is ChatListUIState.Data -> {
                    errorState.value = null
                    state.value = it.response

                }
                is ChatListUIState.Error -> {
                    errorState.value = "Something went wrong"
                }
                ChatListUIState.Loading -> {
                    errorState.value = null
                    isLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

     fun getCurrentMessageList(id: Int): List<Message> {
        state.value?: emptyList()
        return state.value?.find {
            it.id == id
        }?.messageList ?: emptyList()
    }
}