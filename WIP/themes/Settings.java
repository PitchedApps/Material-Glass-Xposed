package com.pitchedapps.material.glass.xposed.themes;

import android.content.res.ColorStateList;
import android.widget.Button;

import com.pitchedapps.material.glass.xposed.utilities.Common;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class Settings implements IXposedHookInitPackageResources, IXposedHookZygoteInit {

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

        if (!resparam.packageName.equals("com.android.settings")) {
            return;
        }

        if (!(prefs.getBoolean(Common.MASTER_TOGGLE, false) && prefs.getBoolean("Settings", false))) {
            return;
        }

        Common.t("Settings");

        try {
            resparam.res.hookLayout("com.android.settings", "layout", "single_button_panel", new XC_LayoutInflated() {
                @Override
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                Button button = (Button) liparam.view.findViewById(
                        liparam.res.getIdentifier("button", "id", "com.android.settings"));
                button.setBackgroundTintList(ColorStateList.valueOf(0x30000000));
                button.setShadowLayer(0.0f, 0.0f, 0.0f, 0x00000000);
                }
            });
        } catch (Exception e) {
            Common.xLog("Settings button error " + e);
        }

    }

}
