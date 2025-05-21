package com.girish.task.ui.presentation.viewmodel.account_list

sealed class AccountListUiEvents {

    data object SearchByQuery : AccountListUiEvents()
    data class OnSearchQueryChange(val query: String) : AccountListUiEvents()

}