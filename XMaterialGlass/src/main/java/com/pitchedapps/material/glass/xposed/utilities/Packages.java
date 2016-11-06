package com.pitchedapps.material.glass.xposed.utilities;

import com.pitchedapps.material.glass.xposed.themes.Settings;
import com.pitchedapps.material.glass.xposed.themes.ThemeBase;

/**
 * Created by 7681 on 2016-03-12.
 */
public enum Packages {

    ADAWAY("org.adaway", "") {
        @Override
        public ThemeBase getTheme() {
            return null;
        }

        @Override
        public String[] getDescription() {
            return new String[]{"test"};
        }
    },
    INSTAGRAM("com.instagram.android", null) {
        @Override
        public ThemeBase getTheme() {
            return null;
        }

        @Override
        public String[] getDescription() {
            return new String[0];
        }
    },
    SETTINGS("com.android.settings", "") {
        @Override
        public ThemeBase getTheme() {
            return Settings.get();
        }

        @Override
        public String[] getDescription() {
            return new String[0];
        }
    },
    WHATSAPP("com.whatsapp", "") {
        @Override
        public ThemeBase getTheme() {
            return null;
        }

        @Override
        public String[] getDescription() {
            return new String[0];
        }
    },
    XPOSED("de.robv.android.xposed.installer", "") {
        @Override
        public ThemeBase getTheme() {
            return null;
        }

        @Override
        public String[] getDescription() {
            return new String[0];
        }
    };

    private String packageName, prefKey;
    private boolean enabled = true;

    Packages(String packageName, String key) {
        this.packageName = packageName;
        if (key == null) {
            enabled = false;
            prefKey = toString();
        } else if (key.isEmpty()) {
            prefKey = toString();
        } else {
            prefKey = key;
        }
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPrefKey() {
        return prefKey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    public abstract ThemeBase getTheme();

    public abstract String[] getDescription();
}
