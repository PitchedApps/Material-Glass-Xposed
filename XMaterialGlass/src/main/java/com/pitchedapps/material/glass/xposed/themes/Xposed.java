package com.pitchedapps.material.glass.xposed.themes;

import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;

/**
 * Created by 7681 on 2016-02-19.
 */
public class Xposed {

    public static void handleInitPackageResources(InitPackageResourcesParam resparam) {

        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "list_header", 0xff748B96);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_dark", 0x30000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_light", 0x30000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_black", 0x30000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_pressed_dark", 0x30555555);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_pressed_light", 0x30555555);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_background_pressed_black", 0x30555555);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_shadow_dark", 0x00000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_shadow_light", 0x00000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "card_shadow_black", 0x00000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "app_background_light", 0x00000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "app_background_black", 0x00000000);
        resparam.res.setReplacement("de.robv.android.xposed.installer", "color", "pager_tab_strip_bg_dark", 0x30000000);
    }
}
