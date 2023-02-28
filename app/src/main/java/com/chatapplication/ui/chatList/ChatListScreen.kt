package com.chatapplication.ui.chatList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun ChatListScreen(navigationCallback: NavHostController?) {
    val viewModel: ChatListViewModel = viewModel()
    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        items(viewModel.chatList.value) { user ->
            ChatUser(user, navigationCallback)
        }
    }
}

@Composable
private fun ChatUser(user: String, navigationCallback: NavHostController?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigationCallback?.navigate("chat_message_route") }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(user)
            UserContent(user)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ProfilePicture(user: String) {
    Card(shape = CircleShape, border = BorderStroke(width = 2.dp, color = Color.Blue)) {
        GlideImage(
            model = "https://xsgames.co/randomusers/avatar.php?g=male",
            contentDescription = user,
            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
private fun UserContent(user: String) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                text = user,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = "12:00 PM",
                fontSize = 12.sp
            )
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier.align(Alignment.TopStart), text = "Message")
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = "2",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ChatListScreen(null)
}