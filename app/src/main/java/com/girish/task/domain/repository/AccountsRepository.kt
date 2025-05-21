package com.girish.task.domain.repository

import com.girish.task.domain.model.AccountData
import com.girish.task.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {

    fun getAllAccounts(): Flow<ApiResponse<List<AccountData>>>
    fun getAccountById(accountId: Int): Flow<ApiResponse<AccountData>>
    fun searchByQuery(query: String): Flow<ApiResponse<List<AccountData>>>

    fun addNewAccount(accountData: AccountData): Flow<ApiResponse<Unit>>
    fun deleteAccount(accountId: Int): Flow<ApiResponse<Unit>>
    fun updateAccount(accountData: AccountData): Flow<ApiResponse<Unit>>

    fun getWebsiteName(searchName: String): Flow<ApiResponse<String>>

}