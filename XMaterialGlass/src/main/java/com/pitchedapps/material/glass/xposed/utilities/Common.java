package com.pitchedapps.material.glass.xposed.utilities;

import android.util.Log;

import de.robv.android.xposed.XposedBridge;

public class Common {

    public static String PACKAGE_NAME = "com.pitchedapps.material.glass.xposed";

    public static void xLog (Object o) {
        XposedBridge.log("MGX: " + o.toString());
    }

    public static void log (Object o) {
        Log.d("MGX", o.toString());
    }

    public static void e (Object o) {
        Log.e("MGX", o.toString());
    }

    public static void r (String s) {
        Log.d("MGX", s + " is running.");
    }

    public static void xLogError (Exception e) {
        xLog("Error Message: " + e.getMessage());
        xLog("Error Cause: " + e.getCause() + "\n");
    }

}
