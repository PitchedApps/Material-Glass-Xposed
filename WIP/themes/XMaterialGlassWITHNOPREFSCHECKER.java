package com.pitchedapps.material.glass.xposed;

import android.app.Activity;
import android.content.res.XResources;
import android.os.Bundle;

import com.pitchedapps.material.glass.xposed.themes.Instagram;
import com.pitchedapps.material.glass.xposed.themes.Settings;
import com.pitchedapps.material.glass.xposed.themes.Xposed;
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
public class XMaterialGlass implements IXposedHookZygoteInit, IXposedHookLoadPackage, IXposedHookInitPackageResources {

    public static String MODULE_PATH = null;
    public XSharedPreferences prefs;
    public boolean noPrefs = false;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
        prefs = new XSharedPreferences(Common.PACKAGE_NAME);
        prefs.makeWorldReadable();
    }

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        prefs.reload();

        if (prefs.getAll().toString().length() < 5 ) {
            noPrefs = true;
        } else {
            noPrefs = false;
        }

        if (!noPrefs) {
            if (!(prefs.getBoolean(Common.MASTER_TOGGLE, false))) {
                return;
            }
        }

        if (resparam.packageName.equals("de.robv.android.xposed.installer") && (noPrefs || prefs.getBoolean("Xposed", false))) {
            Common.t("Xposed");
            Xposed.handleInitPackageResources(resparam);
        }

        if (resparam.packageName.equals("com.android.settings") && (noPrefs || prefs.getBoolean("Settings", false))) {
            Common.t("Settings");
            Settings.handleInitPackageResources(resparam);
        }

        if (resparam.packageName.equals("com.instagram.android") && (noPrefs || prefs.getBoolean("Instagram", false))) {
            Common.t("Instagram");
            Instagram.handleInitPackageResources(resparam, MODULE_PATH);
        }

        if (resparam.packageName.equals("org.adaway") && (noPrefs || prefs.getBoolean("Adaway_layers", false))) {
            Common.t("Adaway");
            XResources.setSystemWideReplacement("android", "color", "primary_material_dark", 0xFFB71C1C);
        }

    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        prefs.reload();

        if (prefs.getAll().toString().length() < 5 ) {
            noPrefs = true;
        } else {
            noPrefs = false;
        }

        if (lpparam.packageName.equals("com.pitchedapps.material.glass.xposed")) { //give info to module
            findAndHookMethod("com.pitchedapps.material.glass.xposed.MainActivity", lpparam.classLoader, "isModuleActivated", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }
            });

            Common.log("full prefs " + prefs.getAll().toString());
            Common.log("full prefs " + prefs.getAll().toString().length());

            if (noPrefs) {
                findAndHookMethod("com.pitchedapps.material.glass.xposed.MainActivity", lpparam.classLoader, "arePrefsWorking", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(false);
                    }
                });
                Common.log("noPrefs is true and changed");
            }
        }

        if (!noPrefs) {
            if (!(prefs.getBoolean(Common.MASTER_TOGGLE, false))) {
                return;
            }
            Common.xLog("no prefs should be false and master toggle should be true");
        }

        if (lpparam.packageName.equals("de.robv.android.xposed.installer") && (noPrefs || prefs.getBoolean("Xposed", false))) {
            Common.xLog("noPrefs is " + noPrefs + " and xposed is " + prefs.getBoolean("Xposed", false));
            final Class<?> XposedInstallerBase = findClass("de.robv.android.xposed.installer.XposedBaseActivity", lpparam.classLoader);
            themeBase(XposedInstallerBase);
        }

        if (lpparam.packageName.equals("com.instagram.android") && (noPrefs || prefs.getBoolean("Instagram", false))) {
            themeTintBase();
        }

        if (lpparam.packageName.equals("org.adaway") && (noPrefs || prefs.getBoolean("Adaway_layers", false))) {
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
