package com.github.offile.activityresult.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.RejectedExecutionException

object ThreadUtil {

    val mainExecutor: Executor = MainExecutor(Handler(Looper.getMainLooper()))

    private class MainExecutor(private val mHandler: Handler) : Executor {
        override fun execute(command: Runnable) {
            if (!mHandler.post(command)) {
                throw RejectedExecutionException("$mHandler is shutting down")
            }
        }
    }

    /**
     * Execute the main thread code synchronously.
     * if it is called by the main thread, execute the code directly,
     * and other threads will block until the main thread execution is completed
     */
    inline fun syncRunOnUiThread(crossinline block: () -> Unit) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            block()
        } else {
            val lock = Object()
            synchronized(lock){
                mainExecutor.execute {
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