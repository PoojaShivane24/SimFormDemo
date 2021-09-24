package com.example.simformdemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.net.NetworkInfo




class NetworkUtils {

    companion object{

        fun isInternetAvailable(context: Context): Boolean? {
            var status: String? = null
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    status = "Wifi enabled"
                    return true
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    status = "Mobile data enabled"
                    return true
                }
            } else {
                status = "No internet is available"
                return false
            }
            return false
        }
    }
}