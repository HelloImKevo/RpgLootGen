package com.schanz.ktapp

import android.annotation.TargetApi
import android.app.Application
import android.os.Build
import androidx.preference.PreferenceManager
import com.schanz.ktapp.data.Creation
import java.util.*

class MainApp : Application() {

    // Declare "static" methods and variables in the companion object.
    companion object {
        // Special prefixes are not idiomatic in Kotlin. Refer to the official style guide:
        // https://developer.android.com/kotlin/style-guide#naming_2
        lateinit var sSingleton: MainApp

        private const val STORED_PREFERENCE_USERNAME = "storedUsername"
        private const val STORED_PREFERENCE_PASSWORD = "storedPassword"

        const val ACHIEVEMENT_HIT_RANDOM = 10

        @JvmField
        var sHitsRandom = 0

        @JvmStatic // For interoperability with existing Java code.
        fun getInstance(): MainApp {
            return sSingleton
        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        fun storeUsernamePreference(lastUsername: String?) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(sSingleton)
            val preferencesEditor = preferences.edit()
            preferencesEditor.putString(STORED_PREFERENCE_USERNAME, lastUsername)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                preferencesEditor.apply()
            } else {
                preferencesEditor.commit()
            }
        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        fun storePasswordPreference(lastPassword: String?) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(sSingleton)
            val preferencesEditor = preferences.edit()
            preferencesEditor.putString(STORED_PREFERENCE_PASSWORD, lastPassword)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                preferencesEditor.apply()
            } else {
                preferencesEditor.commit()
            }
        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        fun removeStoredPreference(key: String?) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(sSingleton)
            val preferencesEditor = preferences.edit()
            preferencesEditor.remove(key)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                preferencesEditor.apply()
            } else {
                preferencesEditor.commit()
            }
        }

        val storedUsername: String?
            get() {
                val preferences = PreferenceManager.getDefaultSharedPreferences(sSingleton)
                return preferences.getString(STORED_PREFERENCE_USERNAME, null)
            }

        val storedPassword: String?
            get() {
                val preferences = PreferenceManager.getDefaultSharedPreferences(sSingleton)
                return preferences.getString(STORED_PREFERENCE_PASSWORD, null)
            }
    }

    // Non-static methods and variables.
    override fun onCreate() {
        super.onCreate()
        sSingleton = this
    }

    var creations: List<Creation> = ArrayList<Creation>()
    var currentCreation: Creation = Creation()

    /**
     * Getter and setter for the `CreationType` of the current global `Creation` object.
     *
     * @return The current object's `CreationType`, or null.
     */
    var currentCreationType: Constants.CreationType?
        get() = currentCreation.creationType
        set(creationType) {
            currentCreation.creationType = creationType
        }

    /**
     * Checks if the given **drawable** resource name exists within this application.
     *
     * @param resourceName The name of the image resource.
     * @return `true` if the resource exists, `false` if it does not.
     */
    fun doesResourceExist(resourceName: String?): Boolean {
        val exists = resources.getIdentifier(resourceName, "drawable", packageName)
        return exists != 0
    }
}
