package com.arjun1194.myapplication.data.model



class ChatListResponse : ArrayList<ChatListResponseItem>()

data class ChatListResponseItem(
    val id: Int,
    val imageURL: String,
    val latestMessageTimestamp: Long,
    val messageList: List<Message>,
    val orderId: String,
    val title: String
)

data class Message(
    val message: String,
    val messageId: String,
    val messageType: String,
    val options: List<Option>,
    val sender: String,
    val timestamp: Long
)

data class Option(
    val optionSubText: String,
    val optionText: String
)