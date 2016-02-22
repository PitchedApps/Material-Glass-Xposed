package com.pitchedapps.material.glass.xposed.themes;

import android.view.View;

import com.pitchedapps.material.glass.xposed.utilities.Common;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

/**
 * Created by 7681 on 2016-02-19.
 */
public class ThemeSettings implements IXposedHookZygoteInit, IXposedHookInitPackageResources {

    public XSharedPreferences prefs;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        prefs = new XSharedPreferences(Common.PACKAGE_NAME);
        prefs.makeWorldReadable();

    }

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
        prefs.reload();

        if (!resparam.packageName.equals("com.android.settings")) {
            return;
        }

        if (!prefs.getBoolean("Settings", false) || !prefs.getBoolean("master_toggle", false)) {
            return;
        }

        try {
            resparam.res.hookLayout("com.android.settings", "layout", "single_button_panel", new XC_LayoutInflated() {
                @Override
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    Common.xLog("got layout");
                    View button = liparam.view.findViewById(liparam.res.getIdentifier("button", "id", "com.android.settings"));
                    Common.xLog("button " + button);
//                    button.setBackgroundColor(ContextCompat.getColor(MainActivity.class, android.R.color.holo_red_dark));
//                    button.setBackgroundTintList(ColorStateList.valueOf(0xFF0000));
                }
            });
        } catch (Exception e) {
            Common.xLogError(e);
        }
    }

}
