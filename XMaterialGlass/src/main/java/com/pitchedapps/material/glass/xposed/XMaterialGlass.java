package com.pitchedapps.material.glass.xposed;

import android.app.Activity;
import android.content.res.XResources;
import android.os.Bundle;

import com.pitchedapps.material.glass.xposed.themes.Instagram;
import com.pitchedapps.material.glass.xposed.themes.Settings;
import com.pitchedapps.material.glass.xposed.themes.Whatsapp;
import com.pitchedapps.material.glass.xposed.themes.Xposed;
import com.pitchedapps.material.glass.xposed.utilities.Common;
import com.pitchedapps.material.glass.xposed.utilities.PackageName;

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
public class XMaterialGlass implements IXposedHookZygoteInit, IXposedHookLoadPackage, IXposedHookInitPackageResources {

    public static String MODULE_PATH = null;
    public XSharedPreferences prefs;
    public boolean prefsEnabled = false;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
        prefs = new XSharedPreferences(Common.PACKAGE_NAME);
        prefs.makeWorldReadable();
    }

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
//        prefs.reload();

        if (!resparam.packageName.equals(Common.PACKAGE_NAME)) {
            prefs.reload();
            prefsEnabled = prefs.getBoolean("arePrefsWorking", false);
        } else {
            return; //opened the module, there's nothing to edit
        }

        if (resparam.packageName.equals("de.robv.android.xposed.installer") && (!prefsEnabled || prefs.getBoolean("Xposed", false))) {
            Common.t("Xposed");
            Xposed.handleInitPackageResources(resparam);
        }

        if (resparam.packageName.equals(PackageName.SETTINGS) && (!prefsEnabled || prefs.getBoolean("Settings_layers", false))) {
            Common.t("Settings");
            Settings.handleInitPackageResources(resparam);
        }

        if (resparam.packageName.equals(PackageName.INSTAGRAM) && (!prefsEnabled || prefs.getBoolean("Instagram", false))) {
            Common.t("Instagram");
            Instagram.handleInitPackageResources(resparam, MODULE_PATH);
        }

//        if (resparam.packageName.equals("org.adaway") && (!prefsEnabled || prefs.getBoolean("Adaway_layers", false))) {
//            Common.t("Adaway");
//            XResources.setSystemWideReplacement("android", "color", "primary_material_dark", 0xFFB71C1C);
//        }

        if (resparam.packageName.equals(PackageName.WHATSAPP) && (!prefsEnabled || prefs.getBoolean("Whatsapp", false))) {
            Common.t("Whatsapp");
            Whatsapp.handleInitPackageResources(resparam);
        }

    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
//        prefs.reload();

        if (lpparam.packageName.equals(Common.PACKAGE_NAME)) { //give info to module
            findAndHookMethod("com.pitchedapps.material.glass.xposed.MainActivity", lpparam.classLoader, "isModuleActivated", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }
            });

            if (!prefsEnabled) {
                findAndHookMethod("com.pitchedapps.material.glass.xposed.MainActivity", lpparam.classLoader, "arePrefsWorking", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(false);
                    }
                });
                Common.e("Prefs are not working");
            }
        } else {
            prefs.reload();
            prefsEnabled = prefs.getBoolean("arePrefsWorking", false); //Do this when the module is not opened
        }

        if (lpparam.packageName.equals(PackageName.XPOSED)) {
            Common.xLog("XSharedPreferences when in Xposed: " + prefs.getAll());
            if (!prefsEnabled || prefs.getBoolean("Xposed", false)) {
                final Class<?> XposedInstallerBase = findClass("de.robv.android.xposed.installer.XposedBaseActivity", lpparam.classLoader);
                themeBase(XposedInstallerBase);
            }
        }

        if (lpparam.packageName.equals(PackageName.INSTAGRAM) && (!prefsEnabled || prefs.getBoolean("Instagram", false))) {
            themeTintBase();
        }

        if (lpparam.packageName.equals(PackageName.ADAWAY) && (!prefsEnabled || prefs.getBoolean("Adaway_layers", false))) {
            Common.t("Adaway");
            findAndHookMethod(Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    Activity a = (Activity) param.thisObject;
                    a.setTheme(android.R.style.Theme_DeviceDefault);
                    a.getWindow().setStatusBarColor(0xFF9C2020);
                    a.getWindow().setNavigationBarColor(0x88000000);
                }
            });
        }
    }

    //changes base theme
    private void themeBase() {
        findAndHookMethod(Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {
                Activity a = (Activity) param.thisObject;
                a.setTheme(android.R.style.Theme_DeviceDefault);
                a.getWindow().setNavigationBarColor(0x88000000);
            }
        });
    }

    private void themeBase(Class<?> mClass) {
        try {
            findAndHookMethod(mClass, "onCreate", Bundle.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Activity activity = (Activity) param.thisObject;
                activity.setTheme(android.R.style.Theme_DeviceDefault);
                }
            });
        } catch (Exception e) {
            Common.xLog("~~~~~ ThemeBase Class Error ~~~~~");
            Common.xLogError(e);
        }
    }

    private void themeTintBase () {
        findAndHookMethod(Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {
            Activity a = (Activity) param.thisObject;
            a.setTheme(android.R.style.Theme_DeviceDefault_Settings);
            a.getWindow().setNavigationBarColor(0x88000000);
            }
        });
    }

    private void themeTintBase (Class<?> mClass) {
        try {
            findAndHookMethod(mClass, "onCreate", Bundle.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                Activity a = (Activity) param.thisObject;
                a.setTheme(android.R.style.Theme_DeviceDefault_Settings);
                a.getWindow().setNavigationBarColor(0x88000000);
                }
            });
        } catch (Exception e) {
            Common.xLog("~~~~~ ThemeTintBase Class Error ~~~~~");
            Common.xLogError(e);
        }
    }
}
