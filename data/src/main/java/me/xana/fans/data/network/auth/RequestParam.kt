package me.xana.fans.data.network.auth

import java.io.File

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/15 23:23
 */
class RequestParam(val name: String?, val value: String?, val file:File? = null) : Comparable<RequestParam> {

    val isFile: Boolean
        get() = this.file != null

    constructor(name: String, value: Long) : this(name, value.toString())

    constructor(name: String, value: Int) : this(name, value.toString())

    constructor(name: String, file: File?) : this(name, file?.name, file)

    constructor(name: String, value: Double) : this(name, value.toString())

    constructor(name: String, value: Boolean) : this(name, value.toString())

    override fun compareTo(other: RequestParam): Int {
        var compared: Int
        compared = this.name!!.compareTo(other.name!!)
        if (compared == 0) {
            compared = this.value!!.compareTo(other.value!!)
        }
        return compared
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (file?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "RequestParam{" +
                "name='" + name + '\''.toString() +
                ", value='" + value + '\''.toString() +
                '}'.toString()
    }

    companion object {

        fun hasFile(params: List<RequestParam>): Boolean {
            if (params.size == 0) return false
            var containFile = false
            for (param in params) {
                if (param.isFile) {
                    containFile = true
                    break
                }
            }
            return containFile
        }
    }
}