package me.xana.fans.data.util

import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/17 23:02
 */
open class Result<T>(val data: T? = null, val error: Throwable? = null) {
    companion object {
        fun <T> fromData(data: T): Result<T> = Result(data, null)

        fun <T> fromError(error: Throwable): Result<T> = Result(null, error)
    }

    val isError: Boolean
        get() = error != null

    val isSuccess: Boolean
        get() = data != null
}

fun <T> Observable<T>.toResult(): Observable<Result<T>> =
        map { Result.fromData(it) }
                .onErrorResumeNext(Function { Observable.just(Result.fromError(it)) })