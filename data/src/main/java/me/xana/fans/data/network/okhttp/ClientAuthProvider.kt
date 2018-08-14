package me.xana.fans.data.network.okhttp

import me.xana.fans.data.network.auth.AuthToken
import me.xana.fans.data.network.auth.ClientKey
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/13 00:00
 */
abstract class ClientAuthProvider(val authType: AuthType,
                                  val clientKey: ClientKey,
                                  var authToken: AuthToken): Interceptor {

    protected open val headerKey: String = "authentication"
    abstract val authValue: String
    abstract val requestValue: String

    abstract fun isAuthUrl(url: String): Boolean
    abstract fun isRequestUrl(url: String): Boolean

    override fun intercept(chain: Interceptor.Chain?): Response? {
        return chain?.let {
            val request = it.request()
            val url = request.url().toString()
            val newRequest = request.newBuilder().let {
                when {
                    isAuthUrl(url) -> it.header(headerKey, authValue).build()
                    isRequestUrl(url) -> it.header(headerKey, requestValue).build()
                    else -> it.build()
                }
            }
            it.proceed(newRequest)
        }
    }

    enum class AuthType {
        BearerToken,
        BasicAuth,
        OAuth1,
        OAuth2
    }
}