package com.github.offile.activityresult

import android.content.Intent
import android.os.Bundle
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.github.offile.activityresult.callback.ActivityResultCallback
import com.github.offile.activityresult.callback.PermissionsResultCallback

/**
 * Use this class to receive activity result in callback
 */
class ActivityResultHelper {

    companion object {
        /**
         * The unique tag of the ProxyFragment added to the FragmentManager
         * @see ProxyFragment
         * @see FragmentManager
         */
        const val TAG = "ActivityResultHelper\$ProxyFragment"
    }

    private lateinit var fragmentManager: FragmentManager

    /**
     * Only one ProxyFragment exists in a FragmentManager
     */
    private val fragment: ProxyFragment by lazy {
        val proxyFragment = fragmentManager.findFragmentByTag(TAG)
        if (proxyFragment != null) {
            proxyFragment as ProxyFragment
        } else {
            ProxyFragment().also {
                fragmentManager.beginTransaction()
                    .add(it, TAG)
                    .commitNow()
            }
        }
    }

    constructor(fragmentActivity: FragmentActivity) {
        fragmentManager = fragmentActivity.supportFragmentManager
    }

    constructor(fragment: Fragment) {
        fragmentManager = fragment.childFragmentManager
    }

    /**
     * use callback to receive results
     * @see FragmentActivity.startActivityForResult
     * @see Fragment.startActivityForResult
     */
    @MainThread
    fun startActivityForResult(intent: Intent, callback: ActivityResultCallback) {
        startActivityForResult(intent, null, callback)
    }

    /**
     * use callback to receive results
     * @see FragmentActivity.startActivityForResult
     * @see Fragment.startActivityForResult
     */
    @MainThread
    fun startActivityForResult(intent: Intent, options: Bundle?, callback: ActivityResultCallback) {
        fragment.startActivityForResult(intent, options, callback)
    }

    /**
     * use callback to receive results
     * @see FragmentActivity.requestPermissions
     * @see Fragment.requestPermissions
     */
    @MainThread
    fun requestPermissions(vararg permissions: String, callback: PermissionsResultCallback) {
        fragment.requestPermissions(permissions, callback)
    }
}