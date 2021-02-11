package com.github.offile.activityresult

import android.util.SparseArray
import androidx.core.util.isEmpty

/**
 * Manage requestCode and callbacks
 */
internal class CallbackManager<T> {
    /**
     * By default only one callback needs to be saved
     */
    private val callbacks: SparseArray<T> = SparseArray(1)

    /**
     * index of current request code
     */
    private var requestCodeIndex = 0

    /**
     * Generate a new requestCode,
     * If there is only one callback, then the request code is 0,
     * otherwise it is accumulated according to the index.
     */
    private fun nextRequestCode(): Int{
        return if(callbacks.isEmpty()){
            if(requestCodeIndex != 0){
                requestCodeIndex = 0
            }
            requestCodeIndex
        }else{
            requestCodeIndex++
            requestCodeIndex
        }
    }

    /**
     * put a callback
     * @return the request code corresponding to this callback
     */
    @Synchronized fun put(callback: T): Int{
        val requestCode = nextRequestCode()
        callbacks.put(requestCode, callback)
        return requestCode
    }

    /**
     * Consume the callback corresponding to the request code
     */
    @Synchronized fun consume(requestCode: Int): T?{
        val callback: T? = callbacks.get(requestCode)
        callbacks.remove(requestCode)
        return callback
    }

    /**
     * remove all callback
     */
    @Synchronized fun clear(){
        callbacks.clear()
    }
}