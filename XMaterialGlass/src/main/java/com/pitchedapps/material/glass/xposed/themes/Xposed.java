package com.pitchedapps.material.glass.xposed.themes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.pitchedapps.material.glass.xposed.utilities.Common;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Created by 7681 on 2016-02-19.
 */
public class Xposed implements IXposedHookZygoteInit, IXposedHookLoadPackage, IXposedHookInitPackageResources {

    public static String MODULE_PATH = null;
    public XSharedPreferences prefs;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
        prefs = new XSharedPreferences(Common.PACKAGE_NAME);
        prefs.makeWorldReadable();
    }

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        prefs.reload();

        if (!resparam.packageName.equals("de.robv.android.xposed.installer")) {
            return;
        }

        if (!(prefs.getBoolean(Common.MASTER_TOGGLE, false) && prefs.getBoolean("Xposed", false))) {
            return;
        }

        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "list_header", 0xff748B96);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_dark", 0x30000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_light", 0x30000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_black", 0x30000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_pressed_dark", 0x30555555);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_pressed_light", 0x30555555);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_pressed_black", 0x30555555);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_shadow_dark", 0x00000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_shadow_light", 0x00000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_shadow_black", 0x00000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "app_background_light", 0x00000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "app_background_black", 0x00000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "pager_tab_strip_bg_dark", 0x30000000);
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        prefs.reload();

        if (!lpparam.packageName.equals("de.robv.android.xposed.installer")) {
            return;
        }


        if (!(prefs.getBoolean(Common.MASTER_TOGGLE, false) && prefs.getBoolean("Xposed", false))) {
            return;
        }

        final Class<?> XposedInstallerBase = findClass("de.robv.android.xposed.installer.XposedBaseActivity", lpparam.classLoader);

        try {
            findAndHookMethod(XposedInstallerBase, "onCreate", Bundle.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Activity activity = (Activity) param.thisObject;
                    activity.setTheme(android.R.style.Theme_DeviceDefault);
//                    activity.getWindow().setStatusBarColor(0xFF9C2020);
                }
            });
        } catch (Exception e) {
            Common.xLogError(e);
        }
    }
}
