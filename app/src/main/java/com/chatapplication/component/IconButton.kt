package com.chatapplication.component

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconButton(icon: ImageVector, iconColor: Color = Color.White, modifier: Modifier) {
    androidx.compose.material.IconButton(
        onClick = { },
        modifier = modifier
    ) {
        Icon(
            icon,
            contentDescription = "content description",
            tint = iconColor
        )
    }
}