package com.girish.task.data.repository

import com.girish.task.data.local.AccountsLocalDataSource
import com.girish.task.data.local.room.mapper.AccountsDataMapper.toAccountData
import com.girish.task.data.local.room.mapper.AccountsDataMapper.toAccountEntity
import com.girish.task.data.remote.GoogleScraper
import com.girish.task.domain.model.AccountData
import com.girish.task.domain.repository.AccountsRepository
import com.girish.task.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AccountsRepositoryImpl(
    private val accountsLocalDataSource: AccountsLocalDataSource,
    private val googleScraper: GoogleScraper
) : AccountsRepository {

    override fun getAllAccounts(): Flow<ApiResponse<List<AccountData>>> {
        return flow {
            accountsLocalDataSource.getAllAccounts().collect { response ->
                when (response) {

                    is ApiResponse.Error -> {
                        emit(ApiResponse.Error(response.message))
                    }

                    is ApiResponse.Loading -> emit(ApiResponse.Loading)


                    is ApiResponse.Success -> {
                        emit(ApiResponse.Success(response.data.map { it.toAccountData() }))
                    }

                    is ApiResponse.IDLE -> emit(ApiResponse.IDLE)

                }
            }
        }
    }

    override fun getAccountById(accountId: Int): Flow<ApiResponse<AccountData>> {
        return flow {
            accountsLocalDataSource.getAccountById(accountId).collect { response ->
                when (response) {

                    is ApiResponse.Error -> {
                        emit(ApiResponse.Error(response.message))
                    }

                    is ApiResponse.Loading -> emit(ApiResponse.Loading)

                    is ApiResponse.Success -> {
                        emit(
                            ApiResponse.Success(response.data.toAccountData())
                        )
                    }

                    is ApiResponse.IDLE -> emit(ApiResponse.IDLE)
                }
            }
        }
    }

    override fun searchByQuery(query: String): Flow<ApiResponse<List<AccountData>>> {
        return flow {
            accountsLocalDataSource.searchByQuery(query).collect { response ->
                when (response) {

                    is ApiResponse.Loading -> emit(ApiResponse.Loading)

                    is ApiResponse.Error -> {
                        emit(ApiResponse.Error(response.message))
                    }

                    is ApiResponse.Success -> {
                        emit(ApiResponse.Success(response.data.map { it.toAccountData() }))
                    }

                    is ApiResponse.IDLE -> emit(ApiResponse.IDLE)
                }
            }
        }
    }

    override fun addNewAccount(accountData: AccountData): Flow<ApiResponse<Unit>> {
        return flow {
            accountsLocalDataSource.addNewAccount(accountData.toAccountEntity())
                .collect { response ->
                   emit(response)
                }
        }
    }

    override fun deleteAccount(accountId: Int): Flow<ApiResponse<Unit>> {
        return flow {
            accountsLocalDataSource.deleteAccount(accountId).collect { response ->
                emit(response)
            }
        }
    }

    override fun updateAccount(accountData: AccountData): Flow<ApiResponse<Unit>> {
        return flow {
            accountsLocalDataSource.updateAccount(accountData.toAccountEntity())
                .collect { response ->
                    emit(response)
                }
        }
    }

    override fun getWebsiteName(searchName: String): Flow<ApiResponse<String>> {
        return flow {
            googleScraper.getSimilarNamedWebsiteUrl(searchName).collect { response ->
                emit(response)
            }
        }
    }
}