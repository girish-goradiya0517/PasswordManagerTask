package com.girish.task.domain.usecase

import com.girish.task.domain.usecase.accounts.AddNewAccount
import com.girish.task.domain.usecase.accounts.DeleteAccount
import com.girish.task.domain.usecase.accounts.GetAccountById
import com.girish.task.domain.usecase.accounts.GetAllAccounts
import com.girish.task.domain.usecase.accounts.SearchByQuery
import com.girish.task.domain.usecase.accounts.UpdateAccount

data class AccountsDataUseCases(

    val getAllAccounts: GetAllAccounts,
    val getAccountById: GetAccountById,
    val searchByQuery: SearchByQuery,
    val deleteAccount: DeleteAccount,
    val updateAccount: UpdateAccount,
    val addNewAccount: AddNewAccount

)