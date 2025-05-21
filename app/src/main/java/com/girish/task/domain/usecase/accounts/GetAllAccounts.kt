package com.girish.task.domain.usecase.accounts

import com.girish.task.domain.model.AccountData
import com.girish.task.domain.repository.AccountsRepository
import com.girish.task.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class GetAllAccounts(
    private val accountsRepository: AccountsRepository
) {
    operator fun invoke(): Flow<ApiResponse<List<AccountData>>> {
        return accountsRepository.getAllAccounts()
    }
}