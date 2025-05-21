package com.girish.task.di

import androidx.room.Room
import com.girish.task.data.local.AccountsLocalDataSource
import com.girish.task.data.local.room.AccountsDatabase
import com.girish.task.data.local.room.AccountsRoomDataSource
import com.girish.task.data.local.room.dao.AccountsDao
import com.girish.task.data.remote.GoogleScraper
import com.girish.task.data.remote.SimilarNamedWebsiteScraper
import com.girish.task.data.repository.AccountsRepositoryImpl
import com.girish.task.domain.repository.AccountsRepository
import com.girish.task.domain.usecase.AccountsDataUseCases
import com.girish.task.domain.usecase.GoogleScraperUseCases
import com.girish.task.domain.usecase.accounts.AddNewAccount
import com.girish.task.domain.usecase.accounts.DeleteAccount
import com.girish.task.domain.usecase.accounts.GetAccountById
import com.girish.task.domain.usecase.accounts.GetAllAccounts
import com.girish.task.domain.usecase.accounts.SearchByQuery
import com.girish.task.domain.usecase.accounts.UpdateAccount
import com.girish.task.domain.usecase.google.GetWebsiteName
import com.girish.task.ui.presentation.viewmodel.add_account.AddAccountViewModel

import com.girish.task.security.CryptoManager
import com.girish.task.ui.presentation.viewmodel.account_list.AccountListViewModel
import com.girish.task.ui.presentation.viewmodel.show_account.ShowAccountViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainAppModule = module {

    single<AccountsDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AccountsDatabase::class.java,
            name = "AccountsDatabase"
        ).build()
    }

    single<AccountsDao> {
        val localDatabase = get<AccountsDatabase>()
        localDatabase.accountsDao()
    }

    single<AccountsLocalDataSource> {
        AccountsRoomDataSource(accountsDao = get())
    }

    single<AccountsRepository> {
        AccountsRepositoryImpl(
            accountsLocalDataSource = get(),
            googleScraper = get()
        )
    }

    single {
        AccountsDataUseCases(
            getAllAccounts = GetAllAccounts(accountsRepository = get()),
            getAccountById = GetAccountById(accountsRepository = get()),
            searchByQuery = SearchByQuery(accountsRepository = get()),
            deleteAccount = DeleteAccount(accountsRepository = get()),
            updateAccount = UpdateAccount(accountsRepository = get()),
            addNewAccount = AddNewAccount(accountsRepository = get())
        )
    }

    viewModel {
        AccountListViewModel(
            accountsDataUseCases = get(),
        )
    }

    viewModel {
        AddAccountViewModel(
            accountsDataUseCases = get(),
            accountData = get(),
            cryptoManager = get(),
            googleScraperUseCases = get()
        )
    }

    viewModel {
        ShowAccountViewModel(
            accountsDataUseCases = get(),
            accountId = get(),
            cryptoManager = get()
        )
    }

    single { CryptoManager() }

    single<GoogleScraper> { SimilarNamedWebsiteScraper() }

    single {
        GoogleScraperUseCases(
            getWebsiteName = GetWebsiteName(accountsRepository = get())
        )
    }

}