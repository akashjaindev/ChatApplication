package com.chatapplication.ui.chatMessage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ChatMessageViewModel : ViewModel() {
    val messageList: MutableState<List<String>> = mutableStateOf(emptyList<String>())

    init {
        messageList.value = mutableListOf<String>().apply {
            for (position in 0..100) {
                add("User ${position + 1}")
            }
        }
    }
}