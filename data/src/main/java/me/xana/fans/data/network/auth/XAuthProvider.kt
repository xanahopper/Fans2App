package me.xana.fans.data.network.auth

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/15 22:59
 */
class XAuthProvider(clientProvider: ClientProvider, authToken: AuthToken?)
    : ClientAuthProvider(AuthType.XAuth, clientProvider, authToken) {

    override val authValue: String
        get() {
            val timestamp = System.currentTimeMillis() / 1000
            val nonce = System.nanoTime() + RAND.nextInt()
            val oauthHeaderParams = mutableListOf(
                    RequestParam("oauth_consumer_key", clientProvider.clientKey),
                    RequestParam("oauth_signature_method", HMAC_SHA1),
                    RequestParam("oauth_timestamp", timestamp),
                    RequestParam("oauth_nonce", nonce),
                    RequestParam("oauth_version", OAUTH_VERSION1),
                    RequestParam("x_auth_username", authUserName ?: ""),
                    RequestParam("x_auth_password", authPassword ?: ""),
                    RequestParam("x_auth_mode", "client_auth")
            )
            val spec = getSecretSpec(clientProvider, null)
            val signature = getSignature("$method&${encode(constructRequestURL(requestUrl))}&${encode(alignParams(oauthHeaderParams))}", spec)
            oauthHeaderParams.add(RequestParam("oauth_signature", signature))
            return "OAuth ${encodeParameters(oauthHeaderParams)}"
        }

    override val requestValue: String
        get() {
            val timestamp = System.currentTimeMillis() / 1000
            val nonce = System.nanoTime() + RAND.nextInt()
            val oauthHeaderParams = mutableListOf(
                    RequestParam("oauth_consumer_key", clientProvider.clientKey),
                    RequestParam("oauth_signature_method", HMAC_SHA1),
                    RequestParam("oauth_timestamp", timestamp),
                    RequestParam("oauth_nonce", nonce),
                    RequestParam("oauth_version", OAUTH_VERSION1)
            )
            authToken?.let {
                oauthHeaderParams.add(RequestParam("oauth_token", it.token))
            }

            parseGetParams(requestUrl, oauthHeaderParams)
            val spec = getSecretSpec(clientProvider, authToken)
            val signature = getSignature("$method&${encode(constructRequestURL(requestUrl))}&${encode(alignParams(oauthHeaderParams))}", spec)
            oauthHeaderParams.add(RequestParam("oauth_signature", signature))
            return "OAuth ${encodeParameters(oauthHeaderParams)}"
        }

    private fun getSignature(data: String, spec: SecretKeySpec): String {
        val byteHMAC: ByteArray?
        try {
            val mac = Mac.getInstance(HMAC_SHA1)
            mac.init(spec)
            byteHMAC = mac.doFinal(data.toByteArray())
            return Base64.encodeToString(byteHMAC, Base64.DEFAULT)
        } catch (ike: InvalidKeyException) {
            throw AssertionError(ike)
        } catch (nsae: NoSuchAlgorithmException) {
            throw AssertionError(nsae)
        }
    }

    private fun getSecretSpec(clientProvider: ClientProvider, authToken: AuthToken?): SecretKeySpec =
            SecretKeySpec(authToken?.let {
                "${encode(clientProvider.clientSecret)}&${it.secret}".toByteArray()
            } ?: "${encode(clientProvider.clientSecret)}&".toByteArray(),
                    HMAC_SHA1)

    private fun constructRequestURL(url: String): String {
        var result = url
        val index = result.indexOf("?")
        if (-1 != index) {
            result = result.substring(0, index)
        }
        val slashIndex = result.indexOf("/", 8)
        val baseURL = result.substring(0, slashIndex).toLowerCase()
        result = baseURL + result.substring(slashIndex)
        return result
    }

    private fun parseGetParams(url: String,
                               signatureBaseParams: MutableList<RequestParam>) {
        val queryStart = url.indexOf("?")
        if (-1 != queryStart) {
            val queryStrs = url.substring(queryStart + 1).split("&").dropLastWhile { it.isEmpty() }.toTypedArray()
            try {
                for (query in queryStrs) {
                    val split = query.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    if (split.size == 2) {
                        signatureBaseParams.add(RequestParam(
                                URLDecoder.decode(split[0], "UTF-8"),
                                URLDecoder.decode(split[1], "UTF-8")))
                    } else {
                        signatureBaseParams.add(RequestParam(
                                URLDecoder.decode(split[0], "UTF-8"), ""))
                    }
                }
            } catch (ignore: UnsupportedEncodingException) {
            }

        }

    }

    companion object {
        const val OAUTH_VERSION1 = "1.0"
        const val HMAC_SHA1 = "HMAC-SHA1"
        val RAND = Random()
    }
}