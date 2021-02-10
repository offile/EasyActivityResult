package com.github.offile.activityresult.callback

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * receive callback of activity result
 * @see FragmentActivity.onActivityResult
 * @see Fragment.onActivityResult
 */
fun interface ActivityResultCallback {
    fun onActivityResult(resultCode: Int, data: Intent?)
}