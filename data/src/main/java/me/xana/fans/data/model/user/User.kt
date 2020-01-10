package me.xana.fans.data.model.user

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/19 14:02
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo val name: String,
    @ColumnInfo val screen_name: String,
    @ColumnInfo val unique_id: String,
    @ColumnInfo val isProtected: Boolean,
    @ColumnInfo val createdAt: Date,
    @ColumnInfo var following: Boolean = false,
    @ColumnInfo var notifications: Boolean = false,
    @Embedded var info: UserInfo,
    @Embedded var profile: UserProfile
)