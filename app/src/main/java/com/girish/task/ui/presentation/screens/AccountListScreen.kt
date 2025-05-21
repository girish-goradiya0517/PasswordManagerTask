package com.girish.task.ui.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.girish.task.ui.presentation.components.AccountCard
import com.girish.task.ui.presentation.components.ManagerTopAppBar
import com.girish.task.ui.presentation.viewmodel.account_list.AccountListUiEvents
import com.girish.task.ui.presentation.viewmodel.account_list.AccountListUiState
import com.girish.task.ui.presentation.viewmodel.account_list.AccountListViewModel
import com.girish.task.ui.theme.DashboardBackground
import com.girish.task.ui.theme.DashboardCardBackground
import com.girish.task.ui.theme.DashboardFloatingButton
import com.girish.task.ui.theme.DividerColor
import com.girish.task.utils.ApiResponse
import com.girish.task.utils.toast
import org.koin.androidx.compose.koinViewModel

class AccountListScreen : Screen {

    @Composable
    override fun Content() {

        val sheetNavigator = LocalBottomSheetNavigator.current
        val accountsListVm = koinViewModel<AccountListViewModel>()
        val uiState by accountsListVm.accountListUiState.collectAsState()

        AccountListContent(
            uiState = uiState,
            uiEvents = accountsListVm::onEvent,
            navigateToAddNewAccount = { sheetNavigator.show(AddNewAccountScreen()) },
            navigateToAccountDetail = { sheetNavigator.show(ShowAccountDetailScreen(it)) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountListContent(
    modifier: Modifier = Modifier,
    uiState: AccountListUiState,
    uiEvents: (AccountListUiEvents) -> Unit,
    navigateToAddNewAccount: () -> Unit,
    navigateToAccountDetail: (Int) -> Unit
) {

    val context = LocalContext.current
    val lazyState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.background(DashboardBackground).nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column {
                ManagerTopAppBar(
                    scrollBehavior = scrollBehavior,
                )
                HorizontalDivider(color = DividerColor, thickness = 1.dp)
                Spacer(Modifier.height(20.dp))
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddNewAccount,
                containerColor = DashboardFloatingButton
            ) {
                Icon(
                    tint = DashboardCardBackground,
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Account",
                    modifier = Modifier.size(40.dp)
                )
            }
        },
    ) { innerPadding ->

        LazyColumn(
            state = lazyState,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
        ) {
            when (val response = uiState.getAllDataResponse) {
                is ApiResponse.IDLE -> Unit
                is ApiResponse.Error -> {
                    context.toast(response.message)
                }

                is ApiResponse.Loading -> {
                    item {
                        CircularProgressIndicator()
                    }
                }

                is ApiResponse.Success -> {
                    items(response.data.asReversed()) { data ->
                        AccountCard(
                            accountData = data,
                            navigateToAccountDetail = navigateToAccountDetail,
                        )
                    }
                }
            }
        }
    }
}