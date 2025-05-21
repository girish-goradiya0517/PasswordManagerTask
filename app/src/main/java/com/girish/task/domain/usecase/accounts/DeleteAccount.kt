package com.girish.task.domain.usecase.accounts

import com.girish.task.domain.repository.AccountsRepository
import com.girish.task.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class DeleteAccount(
    private val accountsRepository: AccountsRepository
) {
    operator fun invoke(accountId: Int): Flow<ApiResponse<Unit>> {
        return accountsRepository.deleteAccount(accountId)
    }
}