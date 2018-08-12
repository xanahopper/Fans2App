package me.xana.fans.data.repository.dao

import androidx.room.*
import io.reactivex.Observable
import me.xana.fans.data.model.account.Account

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/12 14:21
 */
@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAccount(account: Account)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAccount(account: Account)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAccounts(accounts: List<Account>)

    @Query("SELECT * FROM account ORDER BY orderPriority")
    fun getAllAccount(): Observable<List<Account>>

    @Query("SELECT * FROM account WHERE id = :id ORDER BY orderPriority")
    fun getAccountById(id: Long): Observable<List<Account>>

    @Query("SELECT * FROM account WHERE userId = :userId ORDER BY orderPriority")
    fun getAccountByUserId(userId: String): Observable<List<Account>>
}