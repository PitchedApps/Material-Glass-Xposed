package com.pitchedapps.material.glass.xposed.themes;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.preference.ListPreference;
import android.widget.Button;

import com.pitchedapps.material.glass.xposed.utilities.Common;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Created by 7681 on 2016-02-19.
 */
public class ThemeSettings implements IXposedHookZygoteInit, IXposedHookLoadPackage {

    public XSharedPreferences prefs;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        prefs = new XSharedPreferences(Common.PACKAGE_NAME);
        prefs.makeWorldReadable();

    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (!lpparam.packageName.equals("com.android.settings")) {
            return;
        }

        if (!prefs.getBoolean("Settings", false) || !prefs.getBoolean("master_toggle", false)) {
            return;
        }

//        final Class<?> AppStorageSettings = findClass("com.android.settings.applications.AppStorageSettings", lpparam.classLoader);

        try {
            findAndHookMethod("com.android.settings.applications.AppStorageSettings", lpparam.classLoader, "setupViews", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    ListPreference lp = (ListPreference) param.thisObject;
                    Button button = (Button) param.thisObject;
                    button.setBackgroundTintList(ColorStateList.valueOf(0x330000));
//                    button.setBackgroundColor(0x33000000);
//                    Activity activity = (Activity) param.thisObject;
//                    activity
                }
            });
        } catch (Exception e) {
            Common.xLogError(e);
        }
    }


}
