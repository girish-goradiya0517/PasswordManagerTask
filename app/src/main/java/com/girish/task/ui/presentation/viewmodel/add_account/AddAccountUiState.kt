package com.girish.task.ui.presentation.viewmodel.add_account

import com.girish.task.domain.model.AccountData
import com.girish.task.utils.ApiResponse

data class AddAccountUiState(

    val addNewAccountResponse: ApiResponse<Unit> = ApiResponse.IDLE,
    val getAccountByIdResponse: ApiResponse<AccountData> = ApiResponse.IDLE,
    val updateAccountResponse: ApiResponse<Unit> = ApiResponse.IDLE,

    val accountName: String = "",
    val userName: String = "",
    val password: String = "",

    val accountNameError: String = "",
    val userNameError: String = "",
    val passwordError: String = "",

    val isPasswordValidated: Boolean = false,
    val isUsernameValidated: Boolean = false,
    val isAccountNameValidated: Boolean = false,

    val passStrength: Pair<Float, String> = Pair(0f, ""),

    val getWebsiteNameResponse: ApiResponse<Unit> = ApiResponse.IDLE,
    val websiteUrl: String? = null
)