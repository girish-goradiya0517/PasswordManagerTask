package com.girish.task.ui.presentation.viewmodel.account_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.girish.task.domain.model.AccountData
import com.girish.task.domain.usecase.AccountsDataUseCases
import com.girish.task.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountListViewModel(
    private val accountsDataUseCases: AccountsDataUseCases,
) : ViewModel() {

    private val _accountListUiState = MutableStateFlow(AccountListUiState())
    val accountListUiState = _accountListUiState.asStateFlow()

    fun onEvent(event: AccountListUiEvents) {
        when (event) {
            is AccountListUiEvents.SearchByQuery -> searchByQuery()
            is AccountListUiEvents.OnSearchQueryChange -> onSearchQueryChange(event.query)
        }
    }

    init {
        viewModelScope.launch {
            getAllAccounts()
        }
    }

    private fun onSearchQueryChange(query: String) {
        _accountListUiState.update { state ->
            state.copy(searchQuery = query)
        }
        if (query.isBlank()) {
            getAllAccounts()
        } else {
            searchByQuery()
        }
    }

    private fun getAllAccounts() {
        viewModelScope.launch {
            responseHandler(
                accountsDataUseCases.getAllAccounts()
            )
        }
    }

    private fun searchByQuery() {
        viewModelScope.launch {
            responseHandler(
                accountsDataUseCases.searchByQuery(
                    "%${accountListUiState.value.searchQuery}%"
                )
            )
        }
    }

    private fun responseHandler(apiResponse: Flow<ApiResponse<List<AccountData>>>) {
        viewModelScope.launch {
            apiResponse.collect { response ->
                _accountListUiState.update { state ->
                    state.copy(
                        getAllDataResponse = response
                    )
                }
            }
        }
    }
}