package com.girish.task.ui.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.girish.task.domain.model.AccountData
import com.girish.task.ui.presentation.components.AccountLeadingItem
import com.girish.task.ui.presentation.components.AccountTextField
import com.girish.task.ui.presentation.components.PasswordFieldSupportingContent
import com.girish.task.ui.presentation.viewmodel.add_account.AddAccountUiEvents
import com.girish.task.ui.presentation.viewmodel.add_account.AddAccountUiState
import com.girish.task.ui.presentation.viewmodel.add_account.AddAccountViewModel
import com.girish.task.ui.theme.ButtonBackground
import com.girish.task.ui.theme.DividerColor
import com.girish.task.utils.ApiResponse
import com.girish.task.utils.toast
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

data class AddNewAccountScreen(val accountData: AccountData? = null) : Screen {
    @Composable
    override fun Content() {

        val sheetNavigator = LocalBottomSheetNavigator.current
        val addAccountVm = koinViewModel<com.girish.task.ui.presentation.viewmodel.add_account.AddAccountViewModel>(
            parameters = { parametersOf(accountData ?: AccountData(null, "", "", "")) }
        )
        val uiState by addAccountVm.addAccountUiState.collectAsState()
        AddNewAccountContent(
            uiState = uiState,
            uiEvents = addAccountVm::onEvent,
            sheetNavigator = sheetNavigator,
            accountData = accountData
        )
    }
}

@Composable
private fun AddNewAccountContent(
    modifier: Modifier = Modifier,
    uiState: AddAccountUiState,
    uiEvents: (AddAccountUiEvents) -> Unit,
    sheetNavigator: BottomSheetNavigator,
    accountData: AccountData? = null
) {

    val context = LocalContext.current
    var saveButtonLoading by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = uiState.getWebsiteNameResponse) {
        when (val response = uiState.getWebsiteNameResponse) {
            is ApiResponse.Error -> {
                println("Error: ${response.message}")
            }

            is ApiResponse.IDLE -> saveButtonLoading = false
            is ApiResponse.Loading -> saveButtonLoading = true
            is ApiResponse.Success -> {
//                context.toast("Website url saved...")
            }
        }
    }

    LaunchedEffect(key1 = uiState.addNewAccountResponse) {
        when (val response = uiState.addNewAccountResponse) {
            is ApiResponse.Error -> {
                context.toast(response.message)
            }

            is ApiResponse.Loading -> {
                context.toast("Saving...")
            }

            is ApiResponse.Success -> {
                context.toast("Account Saved")
                sheetNavigator.hide()
            }

            ApiResponse.IDLE -> Unit
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .imePadding()
            .navigationBarsPadding()
            .padding(horizontal = 12.dp, vertical = 20.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 60.dp, start = 15.dp, end = 15.dp)
        ) {

            AccountTextField(
                text = uiState.accountName,
                onTextChange = { uiEvents(AddAccountUiEvents.OnAccountNameChange(it)) },
                isError = uiState.accountNameError.isNotEmpty(),
                label = "Account Name",
                supportingText = { Text(uiState.accountNameError) },
                leadingIcon = {
                    AccountLeadingItem(
                        modifier = Modifier.size(30.dp),
                        title = uiState.accountName,
                        websiteUrl = uiState.websiteUrl
                    )
                }
            )

            AccountTextField(
                text = uiState.userName,
                onTextChange = { uiEvents(AddAccountUiEvents.OnUserNameChange(it)) },
                isError = uiState.userNameError.isNotEmpty(),
                label = "Username/Email",
                supportingText = { Text(uiState.userNameError) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person2,
                        contentDescription = "User"
                    )
                }
            )

            AccountTextField(
                text = uiState.password,
                onTextChange = { uiEvents(AddAccountUiEvents.OnPasswordChange(it)) },
                isError = uiState.passwordError.isNotEmpty(),
                label = "Password",
                isPassword = true,
                imeAction = ImeAction.Done,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Password,
                        contentDescription = "Password"
                    )
                },
                supportingText = {
                    PasswordFieldSupportingContent(
                        errorText = uiState.passwordError,
                        passStrength = uiState.passStrength,
                        generatePassword = { uiEvents(AddAccountUiEvents.GenerateRandomPass(it)) },
                    )
                }
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBackground),
            onClick = {
                uiEvents(AddAccountUiEvents.ValidateForm)
                if (uiState.isAccountNameValidated && uiState.isPasswordValidated && uiState.isUsernameValidated) {
                    uiEvents(AddAccountUiEvents.AddNewAccount)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            if (saveButtonLoading) CircularProgressIndicator()
            else Text(if (accountData?.accountName?.isNotEmpty() == true) "Update Account" else "Add New Account")
        }
    }

}