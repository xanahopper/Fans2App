package me.xana.fans.data.util

import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

/**
 * Project:  Fans2App
 * Author:   Xana Hopper(xanahopper@163.com)
 * Created:  2018/8/16 21:38
 */
class Weak<T : Any>(initializer: () -> T?) {
    private var weakReference = WeakReference(initializer())

    constructor():this({
        null
    })

    constructor(value: T?) : this({ value })

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return weakReference.get()
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        weakReference = WeakReference(value)
    }

}