package com.pitchedapps.material.glass.xposed.themes;

import android.content.res.XModuleResources;

import com.pitchedapps.material.glass.xposed.R;
import com.pitchedapps.material.glass.xposed.utilities.Common;

import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;

/**
 * Created by 7681 on 2016-02-19.
 */
public class ATemplate {

    public static void handleInitPackageResources(InitPackageResourcesParam resparam, String MODULE_PATH) {

        //Initialize values
        String PACKAGE_NAME = "aaa";
        XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);
        Map<String, Integer> color_map = new HashMap<>();
        Map<String, Integer> drawable_map = new HashMap<>();

        color_map.put("a", 0xffffffff);

        drawable_map.put("a", R.drawable.card_background);

        //add all map values
        for (String key : color_map.keySet()) {
            resparam.res.setReplacement(PACKAGE_NAME, "color", key, color_map.get(key));
        }

        for (String key : drawable_map.keySet()) {
            try {
                resparam.res.setReplacement(PACKAGE_NAME, "drawable", key, modRes.fwd(color_map.get(key)));
            } catch (Throwable t) {
                Common.xLog(PACKAGE_NAME + " drawable not found: " + key);
            }
        }

    }

}
