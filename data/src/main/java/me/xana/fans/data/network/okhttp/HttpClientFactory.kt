package me.xana.fans.data.network.okhttp

import me.xana.fans.data.network.auth.ClientAuthProvider
import okhttp3.OkHttpClient

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/12 23:44
 */
object HttpClientFactory {
    const val CLIENT_FANFOU = "fanfou"

    fun createAuthClient(clientName: String, authProvider: ClientAuthProvider): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(authProvider)
        return builder.build()
    }
}