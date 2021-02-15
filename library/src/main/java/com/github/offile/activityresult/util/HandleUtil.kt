package com.github.offile.activityresult.util

import android.os.Handler
import android.os.Looper

object HandleUtil {

    val mainHandle = Handler(Looper.getMainLooper())

    inline fun syncRunOnUiThread(crossinline block: () -> Unit) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            block()
        } else {
            val lock = Object()
            synchronized(lock){
                mainHandle.postAtFrontOfQueue {
                    synchronized(lock){
                        block()
                        lock.notifyAll()
                    }
                }
                lock.wait()
            }
        }
    }
}