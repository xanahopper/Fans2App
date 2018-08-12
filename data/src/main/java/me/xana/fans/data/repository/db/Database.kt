package me.xana.fans.data.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.xana.fans.data.model.account.Account
import me.xana.fans.data.repository.dao.AccountDao

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/12 14:38
 */
@Database(entities = [Account::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}