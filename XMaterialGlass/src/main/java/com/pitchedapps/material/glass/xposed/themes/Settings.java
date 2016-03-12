package com.pitchedapps.material.glass.xposed.themes;

import android.content.res.ColorStateList;
import android.widget.Button;

import com.pitchedapps.material.glass.xposed.utilities.Common;
import com.pitchedapps.material.glass.xposed.utilities.PackageName;

import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class Settings {

    public static void handleInitPackageResources(InitPackageResourcesParam resparam) {

        final String PACKAGE_NAME = PackageName.SETTINGS;

        try {
            resparam.res.hookLayout(PACKAGE_NAME, "layout", "single_button_panel", new XC_LayoutInflated() {
                @Override
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                Button button = (Button) liparam.view.findViewById(
                        liparam.res.getIdentifier("button", "id", PACKAGE_NAME));
                button.setBackgroundTintList(ColorStateList.valueOf(0x30000000));
                button.setShadowLayer(0.0f, 0.0f, 0.0f, 0x00000000);
                }
            });
        } catch (Exception e) {
            Common.xLog("Settings button error " + e);
        }

    }

}
