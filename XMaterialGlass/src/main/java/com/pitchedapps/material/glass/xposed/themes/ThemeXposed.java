package com.pitchedapps.material.glass.xposed.themes;

import android.app.Activity;
import android.os.Bundle;

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
public class ThemeXposed implements IXposedHookZygoteInit, IXposedHookLoadPackage {

    public static String MODULE_PATH = null;
    public XSharedPreferences prefs;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
        prefs = new XSharedPreferences(Common.PACKAGE_NAME);
        prefs.makeWorldReadable();

    }
/*
    @SuppressWarnings("deprecation")
    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        prefs.reload();

        if (!resparam.packageName.equals("de.robv.android.xposed.installer")) {
            return;
        }

        if (!prefs.getBoolean("Xposed", false) || !prefs.getBoolean("master_toggle", false)) {
            return;
        }

        final XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);

        //TODO make this work
        DrawableLoader cardBackground = new DrawableLoader() {
            @Override
            public Drawable newDrawable(XResources xResources, int i) throws Throwable {
                return modRes.getDrawable(R.drawable.card_background);
            }
        };

        resparam.res.setReplacement("de.robv.android.xposed.installer", "drawable", "background_card_light", cardBackground);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "drawable", "background_card_pressed_light", cardBackground);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "drawable", "background_card_dark", cardBackground);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "drawable", "background_card_pressed_dark", cardBackground);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "drawable", "background_card_black", cardBackground);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "drawable", "background_card_pressed_black", cardBackground);
    } */

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        prefs.reload();

        if (!lpparam.packageName.equals("de.robv.android.xposed.installer")) {
            return;
        }

        if (!prefs.getBoolean("Xposed", false) || !prefs.getBoolean("master_toggle", false)) {
            return;
        }

        final Class<?> XposedInstallerBase = findClass("de.robv.android.xposed.installer.XposedBaseActivity", lpparam.classLoader);
        try {
            findAndHookMethod(XposedInstallerBase, "onCreate", Bundle.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Activity activity = (Activity) param.thisObject;
                    activity.setTheme(android.R.style.Theme_DeviceDefault);
                }
            });
        } catch (Exception e) {
            Common.xLogError(e);
        }
    }
}
