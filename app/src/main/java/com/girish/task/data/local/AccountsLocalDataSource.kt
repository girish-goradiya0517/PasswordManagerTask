package com.girish.task.data.local

import com.girish.task.data.local.room.model.AccountEntity
import com.girish.task.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface AccountsLocalDataSource {

    fun getAllAccounts(): Flow<ApiResponse<List<AccountEntity>>>
    fun getAccountById(accountId: Int): Flow<ApiResponse<AccountEntity>>
    fun searchByQuery(query: String): Flow<ApiResponse<List<AccountEntity>>>

    fun addNewAccount(accountEntity: AccountEntity): Flow<ApiResponse<Unit>>
    fun deleteAccount(accountId: Int): Flow<ApiResponse<Unit>>
    fun updateAccount(accountEntity: AccountEntity): Flow<ApiResponse<Unit>>

}