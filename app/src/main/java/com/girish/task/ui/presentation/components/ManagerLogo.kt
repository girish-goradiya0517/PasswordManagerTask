package com.girish.task.ui.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.girish.task.ui.theme.TextColor

@Composable
fun ManagerLogo() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = TextColor,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Password")
            }
            withStyle(
                style = SpanStyle(
                    color = TextColor,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Manager")
            }
        }
    )
}