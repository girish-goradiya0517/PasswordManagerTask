package com.girish.task.ui.presentation.viewmodel.account_list

import com.girish.task.domain.model.AccountData
import com.girish.task.utils.ApiResponse

data class AccountListUiState(

    val getAllDataResponse: ApiResponse<List<AccountData>> = ApiResponse.IDLE,

    val searchQuery: String = ""

)