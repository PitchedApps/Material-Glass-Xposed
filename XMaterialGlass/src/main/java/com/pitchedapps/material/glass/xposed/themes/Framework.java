package com.pitchedapps.material.glass.xposed.themes;

import com.pitchedapps.material.glass.xposed.views.ViewType;

public class Framework extends ThemeBase {

    private static Framework sTheme;

    public static ThemeBase get() {
        if (sTheme == null) sTheme = new Framework();
        return sTheme;
    }

    @Override
    public void handleInitPackageResources() {
        addLayout("notification_material_action", "action0", ViewType.BUTTON_TEXT);
    }

}
