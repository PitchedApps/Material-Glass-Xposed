package com.pitchedapps.material.glass.xposed.themes;

public class Template extends ThemeBase {

    private static Template sTheme;

    public static ThemeBase get() {
        if (sTheme == null) sTheme = new Template();
        return sTheme;
    }

    @Override
    public void handleInitPackageResources() {

    }

}
