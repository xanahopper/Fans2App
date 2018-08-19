package me.xana.fans.domain

import android.content.Context
import me.xana.fans.data.util.Weak

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/19 21:50
 */
abstract class Manager(context: Context) {
    val context by Weak { context }
}