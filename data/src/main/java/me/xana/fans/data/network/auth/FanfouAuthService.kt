package me.xana.fans.data.network.auth

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.xana.fans.data.util.Result
import me.xana.fans.data.util.toResult
import okhttp3.ResponseBody
import retrofit2.http.GET

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/16 21:11
 */
class FanfouAuthService(clientProvider: ClientProvider)
    : AuthService(clientProvider, authBaseUrl) {

    private val authAPI by lazy { retrofit.create(AuthAPI::class.java) }

    override fun authWithUserName(userName: String, password: String): Observable<Result<AuthToken>> {
        authProvider.authUserName = userName
        authProvider.authPassword = password
        return authAPI.auth()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { responseBody ->
                    ObservableSource<AuthToken> {
                        it.onNext(AuthToken.from(responseBody.string()))
                        it.onComplete()
                    }
                }.toResult()
    }

    override fun authWithWeb(authUrl: String) {
        TODO("not implemented")
    }

    interface AuthAPI {
        @GET("/oauth/access_token/")
        fun auth(): Observable<ResponseBody>
    }

    companion object {
        const val authBaseUrl: String = "http://fanfou.com/"
    }
}