package com.pitchedapps.material.glass.xposed.themes;

import android.content.res.ColorStateList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pitchedapps.material.glass.xposed.utilities.Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

/**
 * Created by 7681 on 2016-02-19.
 */
public class Whatsapp {

    public static void handleInitPackageResources(InitPackageResourcesParam resparam) {

        //Initialize values
        final String PACKAGE_NAME = "com.whatsapp";
        List<String> textviewList = new ArrayList<>();
        final Map<String, String> button_map = new HashMap<>();
        final Map<String, String> textview_map = new HashMap<>();
        final Map<String, String> editText_map = new HashMap<>();

        textviewList.add("activity_google_drive_restore");
        textviewList.add("activity_incorrect_app_release_version");
        textviewList.add("activity_insufficient_storage_space");
        textviewList.add("groupchat_info_header");
        textviewList.add("web_page_preview");
        textviewList.add("widget_row");
        textviewList.add("wifi_alert_buttons");

        button_map.put("account_info", "choose_friend_button");

        editText_map.put("change_number", "registration_phone");
        editText_map.put("change_number", "registration_new_phone");
        editText_map.put("delete_account", "registration_phone");
        editText_map.put("describe_problem", "describe_problem_description_et");
        editText_map.put("emoji_edittext_dialog", "edit_text");
        editText_map.put("profile_photo_reminder", "registration_name");
        editText_map.put("registername", "registration_name");
        editText_map.put("registerphone", "registration_phone");

        textview_map.put("change_number", "change_number_old_number");
        textview_map.put("change_number", "change_number_new_number");
        textview_map.put("country_picker_row", "country_name");
        textview_map.put("country_picker_row", "country_en_name");

        for (final String layoutName : textview_map.keySet()) {
            try {
                resparam.res.hookLayout(PACKAGE_NAME, "layout", layoutName, new XC_LayoutInflated() {
                    @Override
                    public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                        TextView t = (TextView) liparam.view.findViewById(
                                liparam.res.getIdentifier(textview_map.get(layoutName), "id", PACKAGE_NAME));
                        t.setTextColor(ColorStateList.valueOf(0xFFFF0000));
                        t.setHintTextColor(ColorStateList.valueOf(0xAAFF0000));
                    }
                });
            } catch (Exception e) {
                Common.xLog(PACKAGE_NAME + " " + layoutName + " layout error " + e);
            }
        }

        for (final String layoutName : button_map.keySet()) {
            try {
                resparam.res.hookLayout(PACKAGE_NAME, "layout", layoutName, new XC_LayoutInflated() {
                    @Override
                    public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                        Button t = (Button) liparam.view.findViewById(
                                liparam.res.getIdentifier(button_map.get(layoutName), "id", PACKAGE_NAME));
                        t.setTextColor(ColorStateList.valueOf(0xFFFF0000));
                        t.setHintTextColor(ColorStateList.valueOf(0xAAFF0000));
                    }
                });
            } catch (Exception e) {
                Common.xLog(PACKAGE_NAME + " " + layoutName + " button error " + e);
            }
        }

        for (final String layoutName : editText_map.keySet()) {
            try {
                resparam.res.hookLayout(PACKAGE_NAME, "layout", layoutName, new XC_LayoutInflated() {
                    @Override
                    public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                        EditText t = (EditText) liparam.view.findViewById(
                                liparam.res.getIdentifier(editText_map.get(layoutName), "id", PACKAGE_NAME));
                        t.setTextColor(ColorStateList.valueOf(0xFFFF0000));
                        t.setHintTextColor(ColorStateList.valueOf(0xAAFF0000));
                    }
                });
            } catch (Exception e) {
                Common.xLog(PACKAGE_NAME + " " + layoutName + " editText error " + e);
            }
        }

    }

}
