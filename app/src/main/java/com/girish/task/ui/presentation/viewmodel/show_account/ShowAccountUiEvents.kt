package com.girish.task.ui.presentation.viewmodel.show_account

sealed class ShowAccountUiEvents {

    data class DeleteAccount(val accountId: Int) : com.girish.task.ui.presentation.viewmodel.show_account.ShowAccountUiEvents()

}