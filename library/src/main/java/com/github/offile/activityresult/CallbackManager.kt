package com.github.offile.activityresult

import android.util.SparseArray
import androidx.core.util.isEmpty

/**
 * Manage requestCode and callbacks
 */
internal class CallbackManager<T> {
    private val callbacks: SparseArray<T> = SparseArray(1)
    private var requestCodeIndex = 0

    /**
     * Generate a new requestCode
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
    fun put(callback: T): Int{
        val requestCode = nextRequestCode()
        callbacks.put(requestCode, callback)
        return requestCode
    }

    /**
     * Consume the callback corresponding to the request code
     */
    fun consume(requestCode: Int): T?{
        val callback: T? = callbacks.get(requestCode)
        callbacks.remove(requestCode)
        return callback
    }

    /**
     * remove all callback
     */
    fun clear(){
        callbacks.clear()
    }
}