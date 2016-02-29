package com.pitchedapps.material.glass.xposed;

import com.pitchedapps.material.glass.xposed.utilities.Common;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * Created by 7681 on 2016-02-19.
 */
public class XMaterialGlass implements IXposedHookZygoteInit, IXposedHookLoadPackage, IXposedHookInitPackageResources {

    public static String MODULE_PATH = null;
    public XSharedPreferences prefs;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
//        Common.xLog("PATH " + MODULE_PATH);
        prefs = new XSharedPreferences(Common.PACKAGE_NAME);
        prefs.makeWorldReadable();
    }

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        prefs.reload();

//        Common.log(prefs.getAll());
//        Common.log("path " + prefs.getFile().getAbsolutePath());

        if (!prefs.getBoolean("master_toggle", false)) {
//            Common.xLog("returning..");
            return;
        }
        Common.xLog("settings are working!");
        Common.xLog("-----\n\n\n\n\n-----");
        }

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        prefs.reload();
//        if (!prefs.getBoolean("master_toggle", false)) {
//            Common.xLog("returning..");
//            return;
//        }
//        Common.xLog("settings are working!");
//        Common.xLog("-----\n\n\n\n\n-----");
    }
}
