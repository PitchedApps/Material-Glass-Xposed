package com.pitchedapps.material.glass.xposed.themes;

import com.pitchedapps.material.glass.xposed.views.ViewType;

/**
 * Created by Allan Wang on 2016-11-06.
 */

public class Contacts extends ThemeBase {
    private static Contacts sTheme;

    public static ThemeBase get() {
        if (sTheme == null) sTheme = new Contacts();
        return sTheme;
    }

    @Override
    public void handleInitPackageResources() {
        addLayout("contact_tile_frequent", "contact_tile_name", ViewType.TEXTVIEW);
    }
}
