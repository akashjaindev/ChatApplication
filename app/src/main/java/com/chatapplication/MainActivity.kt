package com.chatapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chatapplication.component.AppToolBar
import com.chatapplication.theme.ChatApplicationTheme
import com.chatapplication.ui.chatList.ChatListScreen
import com.chatapplication.ui.chatMessage.ChatMessageScreen
import com.chatapplication.ui.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadChatApplication()
        }
    }
}

@Composable
private fun LoadChatApplication() {
    ChatApplicationTheme {
        AppToolBar(title = "Login") { ChatApp(it) }
    }
}

@Composable
private fun ChatApp(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        modifier = Modifier.padding(paddingValues),
        startDestination = "chat_list_route"
    ) {
        composable(route = "login_route") {
            LoginScreen(navController)
        }
        composable(route = "chat_list_route") {
            ChatListScreen(navController)
        }
        composable(route = "chat_message_route") {
            ChatMessageScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoadChatApplication()
}