package com.schanz.lootgen;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.preference.PreferenceManager;

import com.schanz.lootgen.Constants.CreationType;

public class MainApp extends Application {

    private static MainApp sSingleton;

    private static final String STORED_PREFERENCE_USERNAME = "storedUsername";
    private static final String STORED_PREFERENCE_PASSWORD = "storedPassword";

    public static final int ACHIEVEMENT_HIT_RANDOM = 10;

    public static int sHitsRandom = 0;

    private ArrayList<Creation> mCreations = new ArrayList<Creation>();

    private Creation mCurrentCreation = new Creation();

    public static MainApp getInstance() {
        return sSingleton;
    }

    public Creation getCurrentCreation() {
        return mCurrentCreation;
    }

    public void setCurrentCreation(Creation currentCreation) {
        mCurrentCreation = currentCreation;
    }

    /**
     * Sets the {@code CreationType} of the current global {@code Creation} object.
     * @return The current object's {@code CreationType} or null..
     */
    public void setCurrentCreationType(CreationType creationType) {
        mCurrentCreation.setCreationType(creationType);
    }

    /**
     * Gets the {@code CreationType} of the current global {@code Creation} object.
     * @return The current object's {@code CreationType} or null.
     */
    public CreationType getCurrentCreationType() {
        return mCurrentCreation.getCreationType();
    }

    /**
     * Checks if the given <b>drawable</b> resource name exists within this application.
     * @param resourceName The name of the image resource.
     * @return {@code true} if the resource exists, {@code false} if it does not.
     */
    public boolean doesResourceExist(String resourceName) {
        int exists = getResources().getIdentifier(resourceName, "drawable", getPackageName());
        return exists != 0 ? true : false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sSingleton = this;
    }

    public ArrayList<Creation> getCreations() {
        return mCreations;
    }

    public void setCreations(ArrayList<Creation> creations) {
        mCreations = creations;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void storeUsernamePreference(String lastUsername) {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(sSingleton);
        Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(STORED_PREFERENCE_USERNAME, lastUsername);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            preferencesEditor.apply();
        } else {
            preferencesEditor.commit();
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void storePasswordPreference(String lastPassword) {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(sSingleton);
        Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(STORED_PREFERENCE_PASSWORD, lastPassword);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            preferencesEditor.apply();
        } else {
            preferencesEditor.commit();
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void removeStoredPreference(String key) {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(sSingleton);
        Editor preferencesEditor = preferences.edit();
        preferencesEditor.remove(key);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            preferencesEditor.apply();
        } else {
            preferencesEditor.commit();
        }
    }

    public static String getStoredUsername() {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(sSingleton);
        return preferences.getString(STORED_PREFERENCE_USERNAME, null);
    }

    public static String getStoredPassword() {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(sSingleton);
        return preferences.getString(STORED_PREFERENCE_PASSWORD, null);
    }
}
