package me.xana.fans.data.network.okhttp

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/13 00:00
 */
abstract class ClientAuthProvider: Interceptor {
    protected open val authKey: String = "authentication"

    abstract val authValue: String

    override fun intercept(chain: Interceptor.Chain?): Response {
        TODO("not implemented")
    }

    enum class AuthType {
        BearerToken,
        BasicAuth,
        OAuth1,
        OAuth2
    }
}