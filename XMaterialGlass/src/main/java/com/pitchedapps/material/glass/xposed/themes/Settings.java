package com.pitchedapps.material.glass.xposed.themes;

import android.content.res.ColorStateList;
import android.widget.Button;

import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class Settings extends ThemeBase {

    private static Settings sTheme;

    public static ThemeBase get() {
        if (sTheme == null) sTheme = new Settings();
        return sTheme;
    }

    @Override
    public void handleInitPackageResources() {
        addLayout("single_button_panel", new XC_LayoutInflated() {
            @Override
            public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                Button button = (Button) liparam.view.findViewById(
                        liparam.res.getIdentifier("button", "id", packageName));
                button.setBackgroundTintList(ColorStateList.valueOf(0x30000000));
                button.setShadowLayer(0.0f, 0.0f, 0.0f, 0x00000000);
            }
        });
    }

}
