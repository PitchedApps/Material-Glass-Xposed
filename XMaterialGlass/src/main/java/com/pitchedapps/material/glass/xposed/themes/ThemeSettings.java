package com.pitchedapps.material.glass.xposed.themes;

import android.widget.Button;

import com.pitchedapps.material.glass.xposed.utilities.Common;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

/**
 * Created by 7681 on 2016-02-19.
 */
public class ThemeSettings implements IXposedHookZygoteInit, IXposedHookInitPackageResources {

    public XSharedPreferences prefs;
    public static String MODULE_PATH = null;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        prefs = new XSharedPreferences(Common.PACKAGE_NAME);
        prefs.makeWorldReadable();
        MODULE_PATH = startupParam.modulePath;
    }

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        prefs.reload();

        if (!resparam.packageName.equals("com.android.settings")) {
            return;
        }

        if (!prefs.getBoolean("Settings", false) || !prefs.getBoolean("master_toggle", false)) {
            return;
        }

        Common.xLog("now settings is running " + resparam.packageName);

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
