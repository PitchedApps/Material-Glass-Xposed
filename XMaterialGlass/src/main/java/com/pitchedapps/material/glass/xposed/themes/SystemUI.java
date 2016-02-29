package com.pitchedapps.material.glass.xposed.themes;

import android.widget.TextView;

import com.pitchedapps.material.glass.xposed.utilities.Common;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by 7681 on 2016-02-19.
 */
public class SystemUI implements IXposedHookZygoteInit, IXposedHookLoadPackage {

    public static XSharedPreferences prefs;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        prefs = new XSharedPreferences(Common.PACKAGE_NAME);
        prefs.makeWorldReadable();

        Common.log("sysssprefs " + prefs.getAll());
    }

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {

        if (!lpparam.packageName.equals("com.android.systemui")) {
            return;
        }

        if (!prefs.getBoolean("SystemUI", false) || !prefs.getBoolean("master_toggle", false)) {
            return;
        }

        Common.r("systemUI");

        findAndHookMethod("com.android.systemui.statusbar.policy.Clock", lpparam.classLoader, "updateClock", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                TextView tv = (TextView) param.thisObject;
                String text = tv.getText().toString();
                tv.setText(text + " :)");
            }
        });
    }
}
