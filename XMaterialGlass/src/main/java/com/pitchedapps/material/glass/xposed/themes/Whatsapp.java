package com.pitchedapps.material.glass.xposed.themes;

import android.content.res.ColorStateList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pitchedapps.material.glass.xposed.utilities.Common;

import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

/**
 * Created by 7681 on 2016-02-19.
 */
public class Whatsapp {

    private static final int textColor = 0xFFFF0000;
    private static final int textColorCache = 0xAAFF0000;
    static final Map<String, Map<Integer, String>> layout_map = new HashMap<>();

    public static void handleInitPackageResources(InitPackageResourcesParam resparam) {

        //Initialize values
        final String PACKAGE_NAME = "com.whatsapp";

        newLayout("account_info", 0, "choose_friend_button");
        newLayout("change_number", 2, "registration_phone");
            addItem("change_number", 2, "registration_new_phone");
        newLayout("delete_account", 2, "registration_phone");
        newLayout("describe_problem", 2, "describe_problem_description_et");
        newLayout("emoji_edittext_dialog", 2, "edit_text");
        newLayout("profile_photo_reminder", 2, "registration_name");
        newLayout("registername", 2, "registration_name");
        newLayout("registerphone", 2, "registration_phone");
        newLayout("change_number", 1, "change_number_old_number");
            addItem("change_number", 1, "change_number_new_number");
        newLayout("country_picker_row", 1, "country_name");
            addItem("country_picker_row", 1, "country_en_name");
        
        

        for (final String layoutName : layout_map.keySet()) {
            try {
                resparam.res.hookLayout(PACKAGE_NAME, "layout", layoutName, new XC_LayoutInflated() {
                    @Override
                    public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                        Map<Integer, String> id_map = layout_map.get(layoutName); //inner hashmap
                        for (final Integer viewType : id_map.keySet()) {
                            switch (viewType) {
                                case 0:
                                    Button t0 = (Button) liparam.view.findViewById(
                                            liparam.res.getIdentifier(id_map.get(viewType), "id", PACKAGE_NAME));
                                    t0.setTextColor(ColorStateList.valueOf(textColor));
                                    t0.setHintTextColor(ColorStateList.valueOf(textColorCache));
                                    break;
                                case 1:
                                    TextView t1 = (TextView) liparam.view.findViewById(
                                            liparam.res.getIdentifier(id_map.get(viewType), "id", PACKAGE_NAME));
                                    t1.setTextColor(ColorStateList.valueOf(textColor));
                                    t1.setHintTextColor(ColorStateList.valueOf(textColorCache));
                                    break;
                                case 2:
                                    EditText t2 = (EditText) liparam.view.findViewById(
                                            liparam.res.getIdentifier(id_map.get(viewType), "id", PACKAGE_NAME));
                                    t2.setTextColor(ColorStateList.valueOf(textColor));
                                    t2.setHintTextColor(ColorStateList.valueOf(textColorCache));
                                    break;
                            }
                        }
                    }
                });
            } catch (Exception e) {
                Common.xLog(PACKAGE_NAME + " " + layoutName + " button error " + e);
            }
        }

    }

    private static void newLayout(String s, Integer i, String id) {
        layout_map.put(s, new HashMap<Integer, String>());
        addItem(s, i, id);
    }

    private static void addItem(String s, Integer i, String id) {
        layout_map.get(s).put(i, id);
    }

}
