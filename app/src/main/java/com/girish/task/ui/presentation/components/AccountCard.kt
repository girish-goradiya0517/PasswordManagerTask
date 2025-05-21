package com.girish.task.ui.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.girish.task.domain.model.AccountData
import com.girish.task.ui.theme.DashboardCardBackground

@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    accountData: AccountData,
    navigateToAccountDetail: (Int) -> Unit
) {
    val maxDisplayLength = 6
    val maskedPassword = "*".repeat(minOf(accountData.accountPassword.length, maxDisplayLength))

    Surface(
        shape = RoundedCornerShape(50),
        color = DashboardCardBackground,
        shadowElevation = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 10.dp)
            .height(60.dp)
            .clickable { accountData.id?.let { navigateToAccountDetail(it) } }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = accountData.accountName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                color = Color.Black,
                modifier = Modifier.wrapContentWidth()
            )

            // Masked password
            Text(
                text = maskedPassword,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )

            // Trailing arrow
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                contentDescription = "Navigate",
                tint = Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .size(18.dp)
                    .wrapContentWidth(Alignment.End)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountCardPreview() {
    val mockAccount = AccountData(
        id = 1,
        accountName = "Facebook",
        accountUsername = "user@example.com",
        accountPassword = "myp@ss123"
    )

    MaterialTheme {
        AccountCard(
            accountData = mockAccount,
            navigateToAccountDetail = { /* no-op for preview */ }
        )
    }
}

