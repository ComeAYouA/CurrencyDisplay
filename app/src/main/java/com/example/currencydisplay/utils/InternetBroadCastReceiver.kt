package com.example.currencydisplay.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


class InternetBroadCastReceiver(
    private val onInternetStatusChanged: (Boolean) -> Unit
): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var internetIsActive = false

        val manager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.getNetworkCapabilities(manager.activeNetwork)

        if (
            networkInfo != null &&
            ( networkInfo.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
              networkInfo.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
              networkInfo.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
        ){
            internetIsActive = true
        }


        onInternetStatusChanged(internetIsActive)
    }
}