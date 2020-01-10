package me.xana.fans.data.model.user

import androidx.room.ColumnInfo

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/19 14:19
 */
data class UserInfo(
    @ColumnInfo val location: String? = null,
    @ColumnInfo val gender: String? = null,
    @ColumnInfo val birthday: String? = null,
    @ColumnInfo val description: String? = null,
    @ColumnInfo val profileImageUrl: String? = null,
    @ColumnInfo val profileImageUrlLarge: String? = null,
    @ColumnInfo val url: String? = null,
    @ColumnInfo val followersCount: Int = 0,
    @ColumnInfo val friendsCount: Int = 0,
    @ColumnInfo val favouritesCount: Int = 0,
    @ColumnInfo val statusesCount: Int = 0,
    @ColumnInfo val photoCount: Int = 0
)