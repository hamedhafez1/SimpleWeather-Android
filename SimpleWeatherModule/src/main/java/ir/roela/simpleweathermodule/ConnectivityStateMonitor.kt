package ir.roela.simpleweathermodule

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class ConnectivityStateMonitor(private val callback: Callback) :
    ConnectivityManager.NetworkCallback() {
    private var networkRequest: NetworkRequest? = null

    init {
        networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
    }

    fun enable(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(networkRequest!!, this)
    }

    override fun onAvailable(network: Network) {
        // Do what you need to do here
        callback.onCallback(true)
    }

}