package com.chatapplication.ui.chatMessage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chatapplication.component.IconButton
import com.chatapplication.theme.Purple700

@Composable
fun ChatMessageScreen() {
    val viewModel: ChatMessageViewModel = viewModel()
    val chatMessage = remember { mutableStateOf(TextFieldValue()) }
    Surface(
        modifier = Modifier.fillMaxSize(), color = Color.LightGray
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth()
            ) {
                items(viewModel.messageList.value) { chatMessage ->
                    ChatMessage(chatMessage)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Surface(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxWidth().height(30.dp)
                                .padding(horizontal = 30.dp)
                        ) {
                            TextField(
                                value = chatMessage.value,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                onValueChange = { chatMessage.value = it })
                        }
                        IconButton(
                            icon = Icons.Default.Face,
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.CenterStart),
                            iconColor = Purple700
                        )
                        IconButton(
                            icon = Icons.Default.Build,
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.CenterEnd),
                            iconColor = Purple700
                        )
                    }
                }
                IconButton(
                    icon = Icons.Default.Send, modifier = Modifier
                        .size(40.dp)
                        .background(shape = CircleShape, color = Purple700)
                )
            }
        }
    }
}

@Composable
private fun ChatMessage(chatMessage: String) {
    Text(modifier = Modifier.fillMaxWidth(), text = chatMessage)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ChatMessageScreen()
}