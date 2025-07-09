package com.fireworks.myeventsdk.Utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Context.WIFI_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresPermission
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


object NetworkUtils {
    val PHONE_LAYOUT = "androidphone"
    val TABLET_LAYOUT = "androidtablet"
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isNetworkAvailable(activity: Activity): Boolean {
        var result = false
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

    fun unixTimeStamp(): Long {
        return System.currentTimeMillis() / 1000L
    }

    /**
     * For API authentication we need to send to server
     *
     * @return
     */
    fun getVCKey(): String {
     val ATRIA_PWD = "fireworks!12*SP#Se"
        val decryptKey = ATRIA_PWD + unixTimeStamp()
        return getHash(decryptKey)
    }


    fun getHash(str: String): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(str.toByteArray(Charsets.UTF_8))
            convertToHex(hashBytes)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            ""
        }
    }
    fun getOsVersion(): String {
        return "Android ${Build.VERSION.RELEASE} (${Build.VERSION.SDK_INT})"
    }

    fun getDeviceName(context: Context): String {
        val manufacturer = Build.MANUFACTURER + " " + Build.MODEL

        return manufacturer.replace(" ", "%20")
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isInternetAvailable(context: Context): Boolean {
        val connectivity = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val networkInfos = connectivity.allNetworkInfo
            if (networkInfos != null)
                for (info in networkInfos) {
                    if (info.state == NetworkInfo.State.CONNECTED) {
                        val speed = getWifiLevel(context)
                        if (speed != -1) {
                            if (speed < 1) {
                                //Toast.makeText(context, "Taking longer than usual, due to your Internet Connection.", Toast.LENGTH_SHORT).show()
                                // snackbarForActivity(context as Activity, "Slow internet connection")
                            }
                        }
                        return true
                    }
                }
        }
        return false
    }



    fun getWifiLevel(context: Context): Int {
        val wifiManager = context.getSystemService(WIFI_SERVICE) as WifiManager
        if (wifiManager != null) {
            val linkSpeed = wifiManager.connectionInfo.rssi
            return WifiManager.calculateSignalLevel(linkSpeed, 5)
        } else {
            return -1
        }
    }

    /**
     * Unique device id
     * @param context
     * @return
     */
    @SuppressLint("HardwareIds")
    fun deviceId(context: Context): String {
        /*val tel: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        var imei = ""
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                imei = tel.imei
            } else {
                imei = tel.deviceId
            }
        }
        return imei*/
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }



    fun getHello(pswd:String): String {
        val dKey = pswd + "dknne3je8k"
        return getHash(dKey)
    }
    fun getDeviceLayoutType(context: Context): String {
        val displayMetrics = context.resources
            .displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return if (dpWidth >= 600) {
            TABLET_LAYOUT
        } else {
            PHONE_LAYOUT
        }
    }


    private fun convertToHex(data: ByteArray): String {
        val buf = StringBuffer()
        for (i in data.indices) {
            var halfbyte = data[i].toInt() ushr 4 and 0x0F
            var two_halfs = 0
            do {
                if (halfbyte in 0..9)
                    buf.append(('0'.toInt() + halfbyte).toChar())
                else
                    buf.append(('a'.toInt() + (halfbyte - 10)).toChar())
                halfbyte = data[i].toInt() and 0x0F
            } while (two_halfs++ < 1)
        }
        return buf.toString()
    }

}