package com.pitchedapps.material.glass.xposed.themes;

import android.content.res.ColorStateList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pitchedapps.material.glass.xposed.utilities.Common;
import com.pitchedapps.material.glass.xposed.utilities.PackageName;

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
        final String PACKAGE_NAME = PackageName.WHATSAPP;

        newButton("account_info", "choose_friend_button");
        newEditText("change_number", "registration_phone");
            addEditText("change_number", "registration_new_phone");
        newEditText("delete_account", "registration_phone");
        newEditText("describe_problem", "describe_problem_description_et");
        newEditText("emoji_edittext_dialog", "edit_text");
        newEditText("profile_photo_reminder", "registration_name");
        newEditText("registername", "registration_name");
        newEditText("registerphone", "registration_phone");
        newTextView("change_number", "change_number_old_number");
            addTextView("change_number", "change_number_new_number");
        newTextView("country_picker_row", "country_name");
        addTextView("country_picker_row", "country_en_name");
        
        

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
    
    private static void newButton(String s, String id) {
        layout_map.put(s, new HashMap<Integer, String>());
        layout_map.get(s).put(0, id);
    }

    private static void newTextView(String s, String id) {
        layout_map.put(s, new HashMap<Integer, String>());
        layout_map.get(s).put(1, id);
    }

    private static void newEditText(String s, String id) {
        layout_map.put(s, new HashMap<Integer, String>());
        layout_map.get(s).put(2, id);
    }

    private static void addButton(String s, String id) {
        layout_map.get(s).put(0, id);
    }

    private static void addTextView(String s, String id) {
        layout_map.get(s).put(1, id);
    }

    private static void addEditText(String s, String id) {
        layout_map.get(s).put(2, id);
    }

}
