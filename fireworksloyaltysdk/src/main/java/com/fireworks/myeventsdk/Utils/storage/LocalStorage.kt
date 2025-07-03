package digital.fireworks.kpdrm.storage

import android.content.Context
import android.util.Log
import com.fireworks.myeventsdk.Utils.Constants


object LocalStorage {

    fun saveStringValue(context: Context, key: String, value: String) {
        val sharedPreferences =
            context.getSharedPreferences("kpdrm_native_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value).apply()
    }

    fun getStringValue(context: Context, key: String, defaultValue: String? = ""): String? {
        val sharedPreferences =
            context.getSharedPreferences("kpdrm_native_preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, defaultValue)
    }

    fun saveIntValue(context: Context, key: String, value: Int) {
        val sharedPreferences =
            context.getSharedPreferences("kpdrm_native_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, value).apply()
    }

    fun getIntValue(context: Context, key: String): Int? {
        val sharedPreferences =
            context.getSharedPreferences("kpdrm_native_preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(key, 0)
    }


    fun saveBooleanValue(context: Context, key: String, value: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences("kpdrm_native_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value).apply()
    }

    fun getBooleanValue(context: Context, key: String): Boolean {
        val sharedPreferences =
            context.getSharedPreferences("kpdrm_native_preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, false)
    }

//    fun getCustomerID(context: Context): String {
//        return getStringValue(context = context, key = Constants.CUSTOMER_ID) ?: ""
//    }
//
//    fun getUserID(context: Context): String {
//        return getStringValue(context = context, key = Constants.SHARED_CUSTOMER_ID) ?: ""
//    }
//
//    fun getCustomerTOKEN(context: Context): String {
//        return getStringValue(context = context, key = Constants.USER_TOKEN) ?: ""
//    }

    fun getLoyaltyID(context: Context):String{
        return getStringValue(context = context, key = "loyalty_id_profile") ?: ""
    }

//    fun getWalletId(context: Context):String{
//        return getStringValue(context = context, key = Constants.WALLET_APP_SP_KEY_WALLET_ID) ?: ""
//    }

    fun getWalletBalance(context: Context):String{
        return getStringValue(context = context, key = "balance") ?: ""
    }
    fun getWalletBalanceFrozen(context: Context):String{
        return getStringValue(context = context, key = "frozen") ?: ""
    }
    fun getCurrentWalletBalance(context: Context):String{
        return getStringValue(context = context, key = "balance")?.toIntOrNull()?.let {
            getStringValue(context = context, key = "frozen")?.toIntOrNull()
                ?.plus(it).toString()
        }?:""
    }

}