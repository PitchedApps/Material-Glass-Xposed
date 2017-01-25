package com.pitchedapps.material.glass.xposed.themes;

import android.content.res.ColorStateList;
import android.widget.Button;

import com.pitchedapps.material.glass.xposed.views.ViewType;

import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class Settings extends ThemeBase {

    private static Settings sTheme;

    public static ThemeBase get() {
        if (sTheme == null) sTheme = new Settings();
        return sTheme;
    }

    @Override
    public void handleInitPackageResources() {
        addLayout("single_button_panel", "button", ViewType.BUTTON);
    }

}
