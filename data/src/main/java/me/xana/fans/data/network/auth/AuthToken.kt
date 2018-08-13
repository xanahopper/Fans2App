package me.xana.fans.data.network.auth

import android.os.Parcel
import android.os.Parcelable

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/12 17:03
 */
data class AuthToken(val token: String? = null, val secret: String? = null) : Parcelable {
    val isNull: Boolean
        get() = (token == null && secret == null)

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(token)
        writeString(secret)
    }

    companion object {
        fun from(response: String?): AuthToken? {
            val tokens = response?.split("&")
            var t: String? = null
            var s: String? = null
            tokens?.forEach {
                if (it.startsWith("oauth_token=")) {
                    t = it.split("=")[1].trim()
                } else if (it.startsWith("oauth_token_secret=")) {
                    s = it.split("=")[1].trim()
                }
            }
            return AuthToken(t, s)
        }

        @JvmField
        val CREATOR: Parcelable.Creator<AuthToken> = object : Parcelable.Creator<AuthToken> {
            override fun createFromParcel(source: Parcel): AuthToken = AuthToken(source)
            override fun newArray(size: Int): Array<AuthToken?> = arrayOfNulls(size)
        }
    }
}