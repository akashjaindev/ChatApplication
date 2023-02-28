package com.chatapplication.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun AppToolBar(title: String, content: @Composable (PaddingValues) -> Unit) {
    return Scaffold(topBar = {
        TopAppBar(title = { Text(title) })
    }) {
        content(it)
    }
}