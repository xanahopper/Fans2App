package me.xana.fans.data.model.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.xana.fans.data.network.auth.AuthToken

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/12 14:11
 */

@Entity(tableName = "account")
data class Account(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo var apiName: String,
    @ColumnInfo var userName: String,
    @ColumnInfo var userId: String = "",
    @ColumnInfo var authToken: String? = null,
    @ColumnInfo var authSecret: String? = null,
    @ColumnInfo var activeState: Int = 0,
    @ColumnInfo var displayName: String? = null,
    @ColumnInfo var displayDescription: String? = null,
    @ColumnInfo var displayAvatar: String? = null,
    @ColumnInfo var orderPriority: Int = -1
) {
    val token: AuthToken
        get() = AuthToken(authToken, authSecret)
}