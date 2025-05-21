package com.girish.task.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.girish.task.data.local.room.dao.AccountsDao
import com.girish.task.data.local.room.model.AccountEntity

@Database(
    entities = [AccountEntity::class],
    version = 1
)
abstract class AccountsDatabase : RoomDatabase() {
    abstract fun accountsDao(): AccountsDao
}