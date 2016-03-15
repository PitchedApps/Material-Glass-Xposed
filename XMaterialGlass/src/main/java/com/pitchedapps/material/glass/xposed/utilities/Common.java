package com.pitchedapps.material.glass.xposed.utilities;

import android.util.Log;

import de.robv.android.xposed.XposedBridge;

public class Common {

    public final static String PACKAGE_NAME = "com.pitchedapps.material.glass.xposed";

    public static void xLog (Object o) {
        try {
            XposedBridge.log("MGX: " + o.toString());
        } catch (Exception e) {
            XposedBridge.log("MGX: " + "ERROR LOGGING");
        }
    }

    public static void log (Object o) {
        try {
            Log.d("MGX", o.toString());
        } catch (Exception e) {
            Log.e("MGX", "ERROR LOGGING");
        }
    }

    public static void e (Object o) {
        try {
            Log.e("MGX", o.toString());
        } catch (Exception e) {
            Log.e("MGX", "ERROR LOGGING");
        }
    }

    public static void t (String s) {
        xLog("~~~~~ " + s + " is themed. ~~~~~");
    }

    public static void xLogError (Exception e) {
        xLog("Error Message: " + e.getMessage());
        xLog("Error Cause: " + e.getCause() + "\n");
    }

}
