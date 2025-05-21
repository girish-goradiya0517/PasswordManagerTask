package com.girish.task.data.local.room.mapper

import com.girish.task.data.local.room.model.AccountEntity
import com.girish.task.domain.model.AccountData

object AccountsDataMapper {

    fun AccountEntity.toAccountData(): AccountData {
        return AccountData(
            id = id,
            accountName = accountName,
            accountUsername = accountUsername,
            accountPassword = accountPassword,
            websiteUrl = websiteUrl
        )
    }

    fun AccountData.toAccountEntity(): AccountEntity {
        return AccountEntity(
            id = id ?: 0,
            accountName = accountName,
            accountUsername = accountUsername,
            accountPassword = accountPassword,
            websiteUrl = websiteUrl
        )
    }
}