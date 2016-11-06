package com.pitchedapps.material.glass.xposed;

import android.app.Activity;
import android.os.Bundle;

import com.pitchedapps.material.glass.xposed.themes.ThemeBase;
import com.pitchedapps.material.glass.xposed.utilities.Common;
import com.pitchedapps.material.glass.xposed.utilities.Packages;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by 7681 on 2016-02-19.
 */
public class XMaterialGlass implements IXposedHookZygoteInit, IXposedHookLoadPackage, IXposedHookInitPackageResources {

    public static String MODULE_PATH = null;
    private XSharedPreferences prefs;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
        prefs = new XSharedPreferences(Common.PACKAGE_NAME);
        prefs.makeWorldReadable();
    }

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        prefs.reload();

        for (Packages app : Packages.values()) {
            if (resparam.packageName.equals(app.getPackageName()) && app.isEnabled() && prefs.getBoolean(app.getPrefKey(), false)) {
                Common.t(app.toString());
                ThemeBase theme = app.getTheme();
                if (theme != null) {
                    theme.handleInitPackageResources(resparam);
                }
                return;
            }
        }

    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        prefs.reload();

        if (lpparam.packageName.equals(Common.PACKAGE_NAME)) { //give info to module
            findAndHookMethod("com.pitchedapps.material.glass.xposed.MainActivity", lpparam.classLoader, "isModuleActivated", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }
            });
            return;
        }

        if (lpparam.packageName.equals(Packages.XPOSED.getPackageName())) {
            Common.xLog("XSharedPreferences when in Xposed: " + prefs.getAll());
            return;
        }

        for (Packages app : Packages.values()) {
            if (lpparam.packageName.equals(app.getPackageName()) && app.isEnabled() && prefs.getBoolean(app.getPrefKey(), false)) {
                Common.t(app.toString());
                ThemeBase theme = app.getTheme();
                if (theme != null) {
                    theme.handleLoadPackage(lpparam);
                }
                return;
            }
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

    private void themeTintBase() {
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

    private void themeTintBase(Class<?> mClass) {
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
