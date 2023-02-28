package com.chatapplication.ui.chatList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ChatListViewModel : ViewModel() {
    val chatList: MutableState<List<String>> = mutableStateOf(emptyList<String>())

    init {
        chatList.value = mutableListOf<String>().apply { add("User 1") }
    }
}