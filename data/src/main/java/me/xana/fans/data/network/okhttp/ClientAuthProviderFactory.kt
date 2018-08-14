package me.xana.fans.data.network.okhttp

import me.xana.fans.data.network.auth.AuthToken
import me.xana.fans.data.network.auth.ClientKey

/**
 * @author  Xana/cuixianming
 * @since   2018-08-14 19:52
 * @version 1.0
 */
object ClientAuthProviderFactory {
    fun createClientAuthProvider(authType: ClientAuthProvider.AuthType,
                                 clientKey: ClientKey,
                                 authToken: AuthToken?): ClientAuthProvider? {
        return when (authType) {
            ClientAuthProvider.AuthType.BasicAuth -> TODO()
            ClientAuthProvider.AuthType.OAuth1 -> TODO()
            else -> null
        }
    }
}