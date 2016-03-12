package com.pitchedapps.material.glass.xposed.themes;

import android.content.res.ColorStateList;
import android.widget.TextView;

import com.pitchedapps.material.glass.xposed.utilities.Common;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

/**
 * Created by 7681 on 2016-02-19.
 */
public class Whatsapp {

    public static void handleInitPackageResources(InitPackageResourcesParam resparam) {

        //Initialize values
        String PACKAGE_NAME = "com.whatsapp";
//        Map<String, Integer> textview_map = new HashMap<>();
        List<String> textviewList = new ArrayList<>();
        textviewList.add("activity_google_drive_restore");
        textviewList.add("activity_incorrect_app_release_version");
        textviewList.add("activity_insufficient_storage_space");
        textviewList.add("groupchat_info_header");
        textviewList.add("web_page_preview");
        textviewList.add("widget_row");
        textviewList.add("wifi_alert_buttons");

        for (String layoutName : textviewList) {
            try {
                resparam.res.hookLayout(PACKAGE_NAME, "layout", layoutName, new XC_LayoutInflated() {
                    @Override
                    public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                        TextView t = (TextView) liparam.view.getParent();
                        t.setTextColor(ColorStateList.valueOf(0xFFFF0000));
                    }
                });
            } catch (Exception e) {
                Common.xLog(PACKAGE_NAME + " " + layoutName + " textview error " + e);
            }
        }

    }

}
