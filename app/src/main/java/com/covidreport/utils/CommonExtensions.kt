package com.covidreport.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View

@Suppress("UNCHECKED_CAST")
fun <T> Context.getSystemServiceAs(serviceName: String): T =
    this.getSystemService(serviceName) as T

fun Context.getConnectivityManager(): ConnectivityManager =
    getSystemServiceAs(Context.CONNECTIVITY_SERVICE)

fun Context.isConnected(): Boolean {
    val cm = this.getConnectivityManager()
    val info = cm.activeNetworkInfo
    return info != null && info.isConnectedOrConnecting
}

//region: View Visibility extensions
fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.invisible(): View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

fun View.gone(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}