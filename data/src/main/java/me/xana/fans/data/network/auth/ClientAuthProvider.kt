package me.xana.fans.data.network.auth

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/13 00:00
 */
abstract class ClientAuthProvider(val authType: AuthType,
                                  val clientProvider: ClientProvider,
                                  var authToken: AuthToken?) : Interceptor {

    protected open val headerKey: String = "authentication"
    abstract val authValue: String
    abstract val requestValue: String

    private fun isAuthUrl(url: String): Boolean = url.startsWith(clientProvider.authUrl, true)
    private fun isRequestUrl(url: String): Boolean = url.startsWith(clientProvider.requestUrl, true)

    var authUserName: String? = null
    var authPassword: String? = null
    var method: String = "GET"
    var requestUrl: String = ""

    override fun intercept(chain: Interceptor.Chain?): Response? {
        return chain?.let { c ->
            val request = c.request()
            val url = request.url().toString()
            method = request.method()
            requestUrl = request.url().toString()
            val newRequest = request.newBuilder().let {
                when {
                    isAuthUrl(url) -> it.header(headerKey, authValue).build()
                    isRequestUrl(url) -> it.header(headerKey, requestValue).build()
                    else -> it.build()
                }
            }
            c.proceed(newRequest)
        }
    }

    protected fun encode(value: String?): String? {
        var encoded: String? = null
        try {
            encoded = URLEncoder.encode(value, "UTF-8")
        } catch (ignore: UnsupportedEncodingException) {
        }

        if (!TextUtils.isEmpty(encoded)) {
            val buf = StringBuilder(encoded!!.length)
            var focus: Char
            var i = 0
            while (i < encoded.length) {
                focus = encoded[i]
                if (focus == '*') {
                    buf.append("%2A")
                } else if (focus == '+') {
                    buf.append("%20")
                } else if (focus == '%' && i + 1 < encoded.length
                        && encoded[i + 1] == '7'
                        && encoded[i + 2] == 'E') {
                    buf.append('~')
                    i += 2
                } else {
                    buf.append(focus)
                }
                i++
            }
            return buf.toString()
        }
        return value
    }

    protected fun alignParams(params: List<RequestParam>): String {
        return encodeParameters(params.sorted())
    }

    protected fun encodeParameters(params: List<RequestParam>): String {
        return encodeParameters(params, "&", false)
    }

    protected fun encodeParameters(params: List<RequestParam>, splitter: String, quote: Boolean): String {
        val buf = StringBuffer()
        for (param in params) {
            if (!param.isFile) {
                if (buf.isNotEmpty()) {
                    if (quote) {
                        buf.append("\"")
                    }
                    buf.append(splitter)
                }
                buf.append(encode(param.name)).append("=")
                if (quote) {
                    buf.append("\"")
                }
                buf.append(encode(param.value))
            }
        }
        if (buf.isNotEmpty()) {
            if (quote) {
                buf.append("\"")
            }
        }
        return buf.toString()
    }
}