package me.xana.fans.data.network.auth

import io.reactivex.Observable
import me.xana.fans.data.network.okhttp.HttpClientFactory
import me.xana.fans.data.util.Result
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/16 21:04
 */
abstract class AuthService(protected val clientProvider: ClientProvider, authBaseUrl: String) {

    protected val authProvider by lazy { ClientAuthProviderFactory.createClientAuthProvider(AuthType.XAuth, clientProvider, null) }
    protected val httpClient by lazy { HttpClientFactory.createAuthClient(HttpClientFactory.CLIENT_FANFOU, authProvider) }
    protected val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(authBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // TODO Scheduler?
                .client(httpClient)
                .build()
    }

    abstract fun authWithUserName(userName: String, password: String): Observable<Result<AuthToken>>

    abstract fun authWithWeb(authUrl: String)

    interface AuthListener {
        fun onWebAuth()

        fun onAuthStart(userName: String)

        fun onAuthSuccess(userName: String, authToken: AuthToken)

        fun onAuthFailed(userName: String, e: Throwable)
    }

}