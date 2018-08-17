package me.xana.fans.data.network.auth

/**
 * @author  Xana/cuixianming
 * @since   2018-08-14 19:52
 * @version 1.0
 */
object ClientAuthProviderFactory {
    fun createClientAuthProvider(authType: AuthType,
                                 clientProvider: ClientProvider,
                                 authToken: AuthToken?): ClientAuthProvider = when (authType) {
        AuthType.BasicAuth -> TODO()
        AuthType.XAuth -> XAuthProvider(clientProvider, authToken)
        AuthType.OAuth1 -> TODO()
    }


}