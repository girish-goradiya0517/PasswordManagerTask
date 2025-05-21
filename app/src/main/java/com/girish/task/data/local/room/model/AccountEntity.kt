package com.girish.task.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.girish.task.utils.AppConstants.ACCOUNTS_TABLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = ACCOUNTS_TABLE)
data class AccountEntity(

    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id: Int = 0,

    @SerialName("account_name")
    val accountName: String,

    @SerialName("account_username")
    val accountUsername: String,

    @SerialName("account_password")
    val accountPassword: String,

    @SerialName("website_url")
    val websiteUrl: String?

)