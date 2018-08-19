package me.xana.fans.data.model.user

import androidx.room.ColumnInfo

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/19 14:23
 */
data class UserProfile(@ColumnInfo val profileBackgroundColor: String? = null,
                       @ColumnInfo val profileTextColor: String? = null,
                       @ColumnInfo val profileLinkColor: String? = null,
                       @ColumnInfo val profileSidebarFillColor: String? = null,
                       @ColumnInfo val profileSidebarBorderColor: String? = null,
                       @ColumnInfo val profileBackgroundImageUrl: String? = null,
                       @ColumnInfo val profileBackgroundTile: Boolean? = false)