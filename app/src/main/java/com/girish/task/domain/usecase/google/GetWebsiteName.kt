package com.girish.task.domain.usecase.google

import com.girish.task.domain.repository.AccountsRepository
import com.girish.task.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class GetWebsiteName(
    private val accountsRepository: AccountsRepository
) {
    operator fun invoke(searchName: String): Flow<ApiResponse<String>> {
        return accountsRepository.getWebsiteName(searchName)
    }
}