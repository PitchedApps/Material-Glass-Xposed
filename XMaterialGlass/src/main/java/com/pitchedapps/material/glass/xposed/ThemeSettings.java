package com.pitchedapps.material.glass.xposed;

import android.app.Activity;
import android.content.res.XResources;
import android.os.Bundle;
import android.widget.Button;

import com.pitchedapps.material.glass.xposed.R;
import com.pitchedapps.material.glass.xposed.utilities.Common;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class ThemeSettings implements IXposedHookInitPackageResources, IXposedHookZygoteInit {

    public static String MODULE_PATH = null;
    public static XSharedPreferences prefs;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
        prefs = new XSharedPreferences(Common.PACKAGE_NAME);
        prefs.makeWorldReadable();
    }

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        prefs.reload();

        if (!prefs.getBoolean("master_toggle", false)) {
            return;
        }

        if (!(prefs.getBoolean("Settings", false) && resparam.packageName.equals("com.android.settings"))) {
            return;
        }


        Common.r("Settings");

        try {
            resparam.res.hookLayout("com.android.settings", "layout", "single_button_panel", new XC_LayoutInflated() {
                @Override
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    Common.xLog("got to here");
                    try {
                        Button button = (Button) liparam.view.findViewById(
                                liparam.res.getIdentifier("button", "id", "com.android.settings"));
                        button.setText("hello");
                        Common.xLog("button text set successfully");
                    } catch (Exception e) {
                        Common.xLog("asdf " + e);
                    }
                }
            });
        } catch (Exception e) {
            Common.xLog("fdsa " + e);
        }

    }

}
