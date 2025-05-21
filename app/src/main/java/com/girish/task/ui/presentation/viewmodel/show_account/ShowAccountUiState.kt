package com.girish.task.ui.presentation.viewmodel.show_account

import com.girish.task.domain.model.AccountData
import com.girish.task.utils.ApiResponse

data class ShowAccountUiState(

    val getAccountByIdResponse: ApiResponse<AccountData> = ApiResponse.IDLE,
    val deleteAccountByIdResponse: ApiResponse<Unit> = ApiResponse.IDLE

)