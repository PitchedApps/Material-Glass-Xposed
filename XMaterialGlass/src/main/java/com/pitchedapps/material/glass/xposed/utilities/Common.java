package com.pitchedapps.material.glass.xposed.utilities;

import android.app.Activity;
import android.util.Log;

import com.pitchedapps.material.glass.xposed.MainActivity;
import com.pitchedapps.material.glass.xposed.R;

import de.robv.android.xposed.XposedBridge;

public class Common extends Activity {

    public static String PACKAGE_NAME = "com.pitchedapps.material.glass.xposed";

    public static void xLog (Object o) {
        XposedBridge.log("MGX: " + o.toString());
    }

    public static void log (Object o) {
        Log.d("MGX", o.toString());
    }

    public static void xLogError (Exception e) {
        xLog("Error Message: " + e.getMessage());
        xLog("Error Cause: " + e.getCause().toString() + "\n");
    }

}
