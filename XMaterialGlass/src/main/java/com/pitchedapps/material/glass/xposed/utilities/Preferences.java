package com.pitchedapps.material.glass.xposed.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by Allan Wang on 2016-11-06.
 */

public class Preferences {
    private static final String
            PREFERENCES_NAME = "x_prefs",
            VERSION_CODE = "version_code";

    private final Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    @SuppressWarnings("deprecation")
    private SharedPreferences prefs() {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_WORLD_READABLE);
    }

    public Map<String, ?> getAll() {
        return prefs().getAll();
    }


    public void setVersionCode(int versionCode) {
        setInt(VERSION_CODE, versionCode);
    }

    public int getVersionCode() {
        return getInt(VERSION_CODE, 0);
    }

    //GENERICS

    protected void setInt(String s, int i) {
        prefs().edit().putInt(s, i).apply();
    }

    protected int getInt(String s, int i) {
        return prefs().getInt(s, i);
    }

    protected void setBoolean(String s, boolean b) {
        prefs().edit().putBoolean(s, b).apply();
    }

    protected boolean getBoolean(String s, boolean b) {
        return prefs().getBoolean(s, b);
    }

    protected void setString(String s, String ss) {
        prefs().edit().putString(s, ss).apply();
    }

    protected String getString(String s, String ss) {
        return prefs().getString(s, ss);
    }
}
