package com.github.offile.activityresult.callback

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * callback for receiving permission result
 * @see FragmentActivity.onRequestPermissionsResult
 * @see Fragment.onRequestPermissionsResult
 */
fun interface PermissionsResultCallback {
    fun onRequestPermissionsResult(
        permissions: Array<String>,
        grantResults: IntArray
    )
}