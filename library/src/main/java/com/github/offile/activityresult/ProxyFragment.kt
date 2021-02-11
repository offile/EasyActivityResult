package com.github.offile.activityresult

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.offile.activityresult.callback.ActivityResultCallback
import com.github.offile.activityresult.callback.PermissionsResultCallback


/**
 * call the method by this fragment proxy
 */
class ProxyFragment : Fragment() {
    private val activityResultCallbacks = CallbackManager<ActivityResultCallback>()
    private val permissionsResultCallbacks = CallbackManager<PermissionsResultCallback>()

    init {
        retainInstance = true
    }

    internal fun startActivityForResult(intent: Intent, options: Bundle?, callback: ActivityResultCallback) {
        val requestCode = activityResultCallbacks.put(callback)
        startActivityForResult(intent, requestCode, options)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val callback = activityResultCallbacks.consume(requestCode)
        callback?.onActivityResult(resultCode, data)
    }

    internal fun requestPermissions(permissions: Array<out String>, callback: PermissionsResultCallback){
        val requestCode = permissionsResultCallbacks.put(callback)
        requestPermissions(permissions, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val callback = permissionsResultCallbacks.consume(requestCode)
        callback?.onRequestPermissionsResult(permissions, grantResults)
    }

    override fun onDestroy() {
        super.onDestroy()
        activityResultCallbacks.clear()
        permissionsResultCallbacks.clear()
    }
}