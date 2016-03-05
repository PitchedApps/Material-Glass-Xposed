package com.pitchedapps.material.glass.xposed;

import android.app.Activity;
import android.content.res.XModuleResources;
import android.os.Bundle;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by 7681 on 2016-02-19.
 */
public class XTest implements IXposedHookZygoteInit, IXposedHookLoadPackage, IXposedHookInitPackageResources {

    public static String MODULE_PATH = null;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
    }

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {

        if (!resparam.packageName.equals("aaa")) {
            return;
        }

//        if (!(prefs.getBoolean(Common.MASTER_TOGGLE, false) && prefs.getBoolean("aaa", false))) {
//            return;
//        }
//        Common.t("aaa");

        XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);
        
        resparam.res.setReplacement("com.instagram.android", "color", "grey_9", 0xffffffff);
        //12/13/2015 action bar button select color
        resparam.res.setReplacement("com.instagram.android", "color", "grey_8", 0x30ffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "grey_7", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "grey_6", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "grey_5", 0xaaffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "grey_4", 0xaaffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "grey_3", 0xddffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "grey_2", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "grey_1_5", 0xaaffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "grey_1", 0xaaffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "grey_0_5", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "blue_9", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "blue_8", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "blue_7", 0xffffffff);
        //12/12/2015 action bar button select color
        resparam.res.setReplacement("com.instagram.android", "color", "blue_6", 0x33ffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "blue_5", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "blue_4", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "blue_3", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "blue_2", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "blue_1", 0x80000000);
        resparam.res.setReplacement("com.instagram.android", "color", "accent_blue_9", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "accent_blue_8", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "accent_blue_7", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "accent_blue_6", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "accent_blue_5", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "accent_blue_4", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "accent_blue_3", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "accent_blue_2", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "accent_blue_1", 0xffffffff);
    
        resparam.res.setReplacement("com.instagram.android", "color", "grey_dark", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "grey_medium", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "grey_light", 0xffffffff);
    
        resparam.res.setReplacement("com.instagram.android", "color", "white", 0x89ABC4);
        resparam.res.setReplacement("com.instagram.android", "color", "white_transparent", 0x80ffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "black", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "black_transparent", 0x80000000);
        resparam.res.setReplacement("com.instagram.android", "color", "black_40_transparent", 0x66000000);
    
        resparam.res.setReplacement("com.instagram.android", "color", "dialog_background", 0x55000000);
        resparam.res.setReplacement("com.instagram.android", "color", "image_placeholder", 0x80000000);
        resparam.res.setReplacement("com.instagram.android", "color", "action_bar_top_highlight", 0x80000000);
        resparam.res.setReplacement("com.instagram.android", "color", "dark_action_bar_top_highlight", 0x80000000);
        resparam.res.setReplacement("com.instagram.android", "color", "action_bar_transparent_background", 0x20000000);
        resparam.res.setReplacement("com.instagram.android", "color", "action_bar_transparent_background_pressed_state", 0x30000000);
        resparam.res.setReplacement("com.instagram.android", "color", "action_bar_semi_transparent_white", 0xbf000000);
        resparam.res.setReplacement("com.instagram.android", "color", "status_bar_blue_background", 0xa0000000);
    
        resparam.res.setReplacement("com.instagram.android", "color", "nux_dayone_email_enabled", 0x20ffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "nux_dayone_email_pressed", 0x30ffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "nux_dayone_log_in_enabled", 0x10ffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "nux_dayone_log_in_pressed", 0x18ffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "multi_reg_token_fill", 0xfffbfbfb);
        resparam.res.setReplacement("com.instagram.android", "color", "multi_reg_token_border", 0x22ffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "multi_reg_token_text", 0xffffffff);
    
        resparam.res.setReplacement("com.instagram.android", "color", "null_state_color", 0x33ffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "seek_bar_inactive_color", 0xaaffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "seek_bar_active_color", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "pill_background", 0xd9000000);
        resparam.res.setReplacement("com.instagram.android", "color", "pill_background_pressed", 0xd9111111);
        resparam.res.setReplacement("com.instagram.android", "color", "pill_background_outline", 0x26ffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "pill_background_outline_pressed", 0x4dffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "video_camcorder_dialog_text_color", 0xffffffff);
    
        resparam.res.setReplacement("com.instagram.android", "color", "camcorder_shutter_outer_ring_disabled", 0x80222222);
    
        resparam.res.setReplacement("com.instagram.android", "color", "filmstrip_dimmer", 0xcd000000);
        resparam.res.setReplacement("com.instagram.android", "color", "camera_shutter_outer_ring", 0xff3383ce);
        resparam.res.setReplacement("com.instagram.android", "color", "camera_shutter_outer_ring_pressed", 0xff20588c);
    
    
    
        resparam.res.setReplacement("com.instagram.android", "color", "light_gray", 0xddffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "gray", 0x80000000);
    
    
    
        resparam.res.setReplacement("com.instagram.android", "color", "iosblue", 0xff0044e2);
    
    
        resparam.res.setReplacement("com.instagram.android", "color", "alt_list_bg_color", 0x80000000);
    
        resparam.res.setReplacement("com.instagram.android", "color", "photo_map_disabled_text", 0xff98d281);
        resparam.res.setReplacement("com.instagram.android", "color", "default_slideout_icon_text_color", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "default_slideout_icon_background", 0xb3000000);
        resparam.res.setReplacement("com.instagram.android", "color", "default_tab_indicator_color", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "default_circle_indicator_fill_color", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "default_circle_indicator_page_color", 0x00000000);
        resparam.res.setReplacement("com.instagram.android", "color", "default_circle_indicator_stroke_color", 0xffdddddd);
        resparam.res.setReplacement("com.instagram.android", "color", "people_tagging_search_background_default", 0x80000000);
        resparam.res.setReplacement("com.instagram.android", "color", "starred_hide_shoutout_color", 0x80222222);
        resparam.res.setReplacement("com.instagram.android", "color", "results_text_color", 0xffffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "result_bar_active_color", 0xff4999da);
        resparam.res.setReplacement("com.instagram.android", "color", "disabled_text_off_white", 0x50ffffff);
        resparam.res.setReplacement("com.instagram.android", "color", "white_30_alpha", 0x4dffffff);
    
        //10/27/2015
        resparam.res.setReplacement("com.instagram.android", "color", "green_6", 0x90111111);
        resparam.res.setReplacement("com.instagram.android", "color", "green_5", 0x90000000);
        resparam.res.setReplacement("com.instagram.android", "color", "green_4", 0x90000000);
    
        //12/13/2015
        resparam.res.setReplacement("com.instagram.android", "color", "grey_0_7", 0x80000000);
        resparam.res.setReplacement("com.instagram.android", "color", "bugreporter_takescreenshot_capture_background", 0x99e5ffe5);
        resparam.res.setReplacement("com.instagram.android", "color", "bugreporter_takescreenshot_cancel_background", 0x99ffe6cc);
        resparam.res.setReplacement("com.instagram.android", "color", "bugreporter_takescreenshot_capture_background_border", 0x995bc25b);
        resparam.res.setReplacement("com.instagram.android", "color", "bugreporter_takescreenshot_cancel_background_border", 0x99ffa736);


        resparam.res.setReplacement("com.instagram.android", "drawable", "action_bar_background", modRes.fwd(R.drawable.instagram_action_bar_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "action_bar_black_button_background", modRes.fwd(R.drawable.instagram_action_bar_button_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "action_bar_blue_button_background", modRes.fwd(R.drawable.instagram_action_bar_button_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "action_bar_dark_button_background", modRes.fwd(R.drawable.instagram_action_bar_button_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "action_bar_green_button_background", modRes.fwd(R.drawable.instagram_action_bar_button_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "action_bar_light_blue_button_background", modRes.fwd(R.drawable.instagram_action_bar_button_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "avatar_background", modRes.fwd(R.drawable.instagram_avatar_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "bg_row_inbox_unread", modRes.fwd(R.drawable.instagram_bg_row_inbox_unread));
        resparam.res.setReplacement("com.instagram.android", "drawable", "bg_simple_row", modRes.fwd(R.drawable.instagram_bg_simple_row));
        resparam.res.setReplacement("com.instagram.android", "drawable", "bg_simple_row_grey", modRes.fwd(R.drawable.instagram_bg_simple_row_grey));
        resparam.res.setReplacement("com.instagram.android", "drawable", "border_bottom_background", modRes.fwd(R.drawable.instagram_border_bottom_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "bubble_grey", modRes.fwd(R.drawable.instagram_bubble));
        resparam.res.setReplacement("com.instagram.android", "drawable", "bubble_grey_clicked", modRes.fwd(R.drawable.instagram_bubble_clicked));
        resparam.res.setReplacement("com.instagram.android", "drawable", "bubble_white", modRes.fwd(R.drawable.instagram_bubble));
        resparam.res.setReplacement("com.instagram.android", "drawable", "bubble_white_clicked", modRes.fwd(R.drawable.instagram_bubble_clicked));
        resparam.res.setReplacement("com.instagram.android", "drawable", "button_blue_background", modRes.fwd(R.drawable.instagram_button_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "button_green_background", modRes.fwd(R.drawable.instagram_button_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "button_grey_background", modRes.fwd(R.drawable.instagram_button_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "button_white_background", modRes.fwd(R.drawable.instagram_button_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "button_white_background", modRes.fwd(R.drawable.instagram_button_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "dark_action_bar_background", modRes.fwd(R.drawable.instagram_dark_action_bar_background));
        resparam.res.setReplacement("com.instagram.android", "drawable", "dialog_bottom_left_button", modRes.fwd(R.drawable.instagram_dialog_bottom_left_button));
        resparam.res.setReplacement("com.instagram.android", "drawable", "dialog_bottom_right_button", modRes.fwd(R.drawable.instagram_dialog_bottom_right_button));
        resparam.res.setReplacement("com.instagram.android", "drawable", "rounded_border_stroke_grey", modRes.fwd(R.drawable.instagram_rounded_border_stroke_grey));
        resparam.res.setReplacement("com.instagram.android", "drawable", "spinner_dropdown", modRes.fwd(R.drawable.instagram_spinner_dropdown));
        resparam.res.setReplacement("com.instagram.android", "drawable", "spinner_dropdown_text", modRes.fwd(R.drawable.instagram_spinner_dropdown_text));

    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (!lpparam.packageName.equals("aaa")) {
            return;
        }

//        if (!(prefs.getBoolean(Common.MASTER_TOGGLE, false) && prefs.getBoolean("aaa", false))) {
//            return;
//        }

        findAndHookMethod(Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param)
                    throws Throwable {
                Activity a = (Activity) param.thisObject;
                a.setTheme(android.R.style.Theme_DeviceDefault_Settings);
                a.getWindow().setNavigationBarColor(0x88000000);

            }
        });

    }
}
