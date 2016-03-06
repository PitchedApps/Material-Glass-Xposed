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
public class Instagram {

    public static void handleInitPackageResources(InitPackageResourcesParam resparam, String MODULE_PATH) {

        //Initialize values
        String PACKAGE_NAME = "com.instagram.android";
        XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);
        Map<String, Integer> color_map = new HashMap<>();
        Map<String, Integer> drawable_map = new HashMap<>();

        color_map.put("grey_9", 0xffffffff);
        //12/13/2015 action bar button select color
        color_map.put("grey_8", 0x30ffffff);
        color_map.put("grey_7", 0xffffffff);
        color_map.put("grey_6", 0xffffffff);
        color_map.put("grey_5", 0xaaffffff);
        color_map.put("grey_4", 0xaaffffff);
        color_map.put("grey_3", 0xddffffff);
        color_map.put("grey_2", 0xffffffff);
        color_map.put("grey_1_5", 0xaaffffff);
        color_map.put("grey_1", 0xaaffffff);
        color_map.put("grey_0_5", 0xffffffff);
        color_map.put("blue_9", 0xffffffff);
        color_map.put("blue_8", 0xffffffff);
        color_map.put("blue_7", 0xffffffff);
        //12/12/2015 action bar button select color
        color_map.put("blue_6", 0x33ffffff);
        color_map.put("blue_5", 0xffffffff);
        color_map.put("blue_4", 0xffffffff);
        color_map.put("blue_3", 0xffffffff);
        color_map.put("blue_2", 0xffffffff);
        color_map.put("blue_1", 0x80000000);
        color_map.put("accent_blue_9", 0xffffffff);
        color_map.put("accent_blue_8", 0xffffffff);
        color_map.put("accent_blue_7", 0xffffffff);
        color_map.put("accent_blue_6", 0xffffffff);
        color_map.put("accent_blue_5", 0xffffffff);
        color_map.put("accent_blue_4", 0xffffffff);
        color_map.put("accent_blue_3", 0xffffffff);
        color_map.put("accent_blue_2", 0xffffffff);
        color_map.put("accent_blue_1", 0xffffffff);

        color_map.put("grey_dark", 0xffffffff);
        color_map.put("grey_medium", 0xffffffff);
        color_map.put("grey_light", 0xffffffff);

        color_map.put("white", 0xff89ABC4);
        color_map.put("white_transparent", 0x80ffffff);
        color_map.put("black", 0xffffffff);
        color_map.put("black_transparent", 0x80000000);
        color_map.put("black_40_transparent", 0x66000000);

        color_map.put("dialog_background", 0x55000000);
        color_map.put("image_placeholder", 0x80000000);
        color_map.put("action_bar_top_highlight", 0x80000000);
        color_map.put("dark_action_bar_top_highlight", 0x80000000);
        color_map.put("action_bar_transparent_background", 0x20000000);
        color_map.put("action_bar_transparent_background_pressed_state", 0x30000000);
        color_map.put("action_bar_semi_transparent_white", 0xbf000000);
        color_map.put("status_bar_blue_background", 0xa0000000);

        color_map.put("nux_dayone_email_enabled", 0x20ffffff);
        color_map.put("nux_dayone_email_pressed", 0x30ffffff);
        color_map.put("nux_dayone_log_in_enabled", 0x10ffffff);
        color_map.put("nux_dayone_log_in_pressed", 0x18ffffff);
        color_map.put("multi_reg_token_fill", 0xfffbfbfb);
        color_map.put("multi_reg_token_border", 0x22ffffff);
        color_map.put("multi_reg_token_text", 0xffffffff);

        color_map.put("null_state_color", 0x33ffffff);
        color_map.put("seek_bar_inactive_color", 0xaaffffff);
        color_map.put("seek_bar_active_color", 0xffffffff);
        color_map.put("pill_background", 0xd9000000);
        color_map.put("pill_background_pressed", 0xd9111111);
        color_map.put("pill_background_outline", 0x26ffffff);
        color_map.put("pill_background_outline_pressed", 0x4dffffff);
        color_map.put("video_camcorder_dialog_text_color", 0xffffffff);

        color_map.put("camcorder_shutter_outer_ring_disabled", 0x80222222);

        color_map.put("filmstrip_dimmer", 0xcd000000);
        color_map.put("camera_shutter_outer_ring", 0xff3383ce);
        color_map.put("camera_shutter_outer_ring_pressed", 0xff20588c);



        color_map.put("light_gray", 0xddffffff);
        color_map.put("gray", 0x80000000);



        color_map.put("iosblue", 0xff0044e2);


        color_map.put("alt_list_bg_color", 0x80000000);

        color_map.put("photo_map_disabled_text", 0xff98d281);
        color_map.put("default_slideout_icon_text_color", 0xffffffff);
        color_map.put("default_slideout_icon_background", 0xb3000000);
        color_map.put("default_tab_indicator_color", 0xffffffff);
        color_map.put("default_circle_indicator_fill_color", 0xffffffff);
        color_map.put("default_circle_indicator_page_color", 0x30000000);
        color_map.put("default_circle_indicator_stroke_color", 0xffdddddd);
        color_map.put("people_tagging_search_background_default", 0x80000000);
        color_map.put("starred_hide_shoutout_color", 0x80222222);
        color_map.put("results_text_color", 0xffffffff);
        color_map.put("result_bar_active_color", 0xff4999da);
        color_map.put("disabled_text_off_white", 0x50ffffff);
        color_map.put("white_30_alpha", 0x4dffffff);

        //10/27/2015
        color_map.put("green_6", 0x90111111);
        color_map.put("green_5", 0x90000000);
        color_map.put("green_4", 0x90000000);

        //12/13/2015
        color_map.put("grey_0_7", 0x80000000);
        color_map.put("bugreporter_takescreenshot_capture_background", 0x99e5ffe5);
        color_map.put("bugreporter_takescreenshot_cancel_background", 0x99ffe6cc);
        color_map.put("bugreporter_takescreenshot_capture_background_border", 0x995bc25b);
        color_map.put("bugreporter_takescreenshot_cancel_background_border", 0x99ffa736);

        //03/05/2016
        color_map.put("blue_medium", 0xffffffff);


        drawable_map.put("action_bar_background", R.drawable.instagram_action_bar_background);
        drawable_map.put("action_bar_black_button_background", R.drawable.instagram_action_bar_button_background);
        drawable_map.put("action_bar_blue_button_background", R.drawable.instagram_action_bar_button_background);
        drawable_map.put("action_bar_dark_button_background", R.drawable.instagram_action_bar_button_background);
        drawable_map.put("action_bar_green_button_background", R.drawable.instagram_action_bar_button_background);
        drawable_map.put("action_bar_light_blue_button_background", R.drawable.instagram_action_bar_button_background);
        drawable_map.put("avatar_background", R.drawable.instagram_avatar_background);
        drawable_map.put("bg_row_inbox_unread", R.drawable.instagram_bg_row_inbox_unread);
        drawable_map.put("bg_simple_row", R.drawable.instagram_bg_simple_row);
        drawable_map.put("bg_simple_row_grey", R.drawable.instagram_bg_simple_row_grey);
        drawable_map.put("border_bottom_background", R.drawable.instagram_border_bottom_background);
        drawable_map.put("bubble_grey", R.drawable.instagram_bubble);
        drawable_map.put("bubble_grey_clicked", R.drawable.instagram_bubble_clicked);
        drawable_map.put("bubble_white", R.drawable.instagram_bubble);
        drawable_map.put("bubble_white_clicked", R.drawable.instagram_bubble_clicked);
        drawable_map.put("button_blue_background", R.drawable.instagram_button_background);
        drawable_map.put("button_green_background", R.drawable.instagram_button_background);
        drawable_map.put("button_grey_background", R.drawable.instagram_button_background);
        drawable_map.put("button_white_background", R.drawable.instagram_button_background);
        drawable_map.put("button_white_background", R.drawable.instagram_button_background);
        drawable_map.put("dark_action_bar_background", R.drawable.instagram_dark_action_bar_background);
        drawable_map.put("dialog_bottom_left_button", R.drawable.instagram_dialog_bottom_left_button);
        drawable_map.put("dialog_bottom_right_button", R.drawable.instagram_dialog_bottom_right_button);
        drawable_map.put("rounded_border_stroke_grey", R.drawable.instagram_rounded_border_stroke_grey);
        drawable_map.put("spinner_dropdown", R.drawable.instagram_spinner_dropdown);
        drawable_map.put("spinner_dropdown_text", R.drawable.instagram_spinner_dropdown_text);

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
