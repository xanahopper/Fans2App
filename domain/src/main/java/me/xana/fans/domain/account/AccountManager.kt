package me.xana.fans.domain.account

import android.content.Context
import io.reactivex.Observable
import me.xana.fans.data.model.account.Account
import me.xana.fans.domain.Manager

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/19 21:46
 */
class AccountManager(context: Context) : Manager(context) {

    val accounts: Observable<MutableList<Account>>
        get() = Observable.empty<MutableList<Account>>()
}