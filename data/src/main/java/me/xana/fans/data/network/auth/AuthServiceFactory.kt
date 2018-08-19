package me.xana.fans.data.network.auth

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/16 21:12
 */
object AuthServiceFactory {
    fun createAuthService(authServiceType: AuthServiceType,clientProvider: ClientProvider): AuthService =
            when (authServiceType) {
                AuthServiceType.Fanfou -> FanfouAuthService(clientProvider)
            }
}