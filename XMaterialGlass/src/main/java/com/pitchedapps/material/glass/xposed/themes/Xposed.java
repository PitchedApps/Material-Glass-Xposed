package com.pitchedapps.material.glass.xposed.themes;

import com.pitchedapps.material.glass.xposed.utilities.PackageName;

import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;

/**
 * Created by 7681 on 2016-02-19.
 */
public class Xposed {

    public static void handleInitPackageResources(InitPackageResourcesParam resparam) {

        final String PACKAGE_NAME = PackageName.XPOSED;
        Map<String, Integer> color_map = new HashMap<>();

        color_map.put("list_header", 0xff748B96);
        color_map.put("card_background_dark", 0x30000000);
        color_map.put("card_background_light", 0x30000000);
        color_map.put("card_background_black", 0x30000000);
        color_map.put("card_background_pressed_dark", 0x30555555);
        color_map.put("card_background_pressed_light", 0x30555555);
        color_map.put("card_background_pressed_black", 0x30555555);
        color_map.put("card_shadow_dark", 0x00000000);
        color_map.put("card_shadow_light", 0x00000000);
        color_map.put("card_shadow_black", 0x00000000);
        color_map.put("app_background_light", 0x00000000);
        color_map.put("app_background_black", 0x00000000);
        color_map.put("pager_tab_strip_bg_dark", 0x30000000);

        for (String key : color_map.keySet()) {
            resparam.res.setReplacement(PACKAGE_NAME, "color", key, color_map.get(key));
        }
    }
}
