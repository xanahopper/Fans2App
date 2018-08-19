package me.xana.fans.data.network.auth

/**
 * @author  Xana/cuixianming
 * @since   2018-08-14 19:52
 * @version 1.0
 */
object ClientAuthProviderFactory {
    fun createClientAuthProvider(clientAuthType: ClientAuthType,
                                 clientProvider: ClientProvider,
                                 authToken: AuthToken?): ClientAuthProvider = when (clientAuthType) {
        ClientAuthType.BasicAuth -> TODO()
        ClientAuthType.XAuth -> XAuthProvider(clientProvider, authToken)
        ClientAuthType.OAuth1 -> TODO()
    }


}