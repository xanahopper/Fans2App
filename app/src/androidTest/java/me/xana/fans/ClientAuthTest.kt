package me.xana.fans

import androidx.test.runner.AndroidJUnit4
import me.xana.fans.data.network.auth.AuthServiceFactory
import me.xana.fans.data.network.auth.AuthServiceType
import me.xana.fans.data.network.auth.ClientProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.HttpException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ClientAuthTest {
    @Test
    fun useAuthService() {
        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getTargetContext()
        val AUTH_URL = "http://fanfou.com/oauth/access_token/"
        val REQUEST_URL = "http://api.fanfou.com/"
        val clientProvider = ClientProvider(BuildConfig.APP_KEY, BuildConfig.APP_SECRET, AUTH_URL, REQUEST_URL)
        val authService = AuthServiceFactory.createAuthService(AuthServiceType.Fanfou, clientProvider)

        authService.authWithUserName("xanahopper@163.com", "BambooLeaf*(!@!!")
                .subscribe(
                        {
                            if (it.isSuccess) {
                                println(it.data?.toString())
                            } else {
                                it.error?.let { err ->
                                    if (err is HttpException) {
                                        val body = err.response().errorBody()?.string()
                                        Assert.fail(body)
                                    }
                                }
                            }
                        },
                        {
                            println(it.message)
                        },
                        {
                            println("Complete")
                        },
                        {
                            println("onSubscribe")
                        })
    }
}
