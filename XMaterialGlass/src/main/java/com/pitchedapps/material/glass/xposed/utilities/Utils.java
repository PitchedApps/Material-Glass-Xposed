package com.pitchedapps.material.glass.xposed.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.pitchedapps.material.glass.xposed.R;

/**
 * @author Aidan Follestad (afollestad)
 */
public class Utils {

    public static String getAppPackageName(Context context) {
        return context.getPackageName();
    }


    public static void showSimpleSnackbar(Context context, View location, String text, int duration) {

        if (duration == 1) {
            Snackbar shortSnackbar = Snackbar.make(location, text,
                    Snackbar.LENGTH_SHORT);
            ViewGroup shortGroup = (ViewGroup) shortSnackbar.getView();
            shortGroup.setBackgroundColor(ContextCompat.getColor(context, R.color.snackbar));
            shortSnackbar.show();

        } else if (duration == 2) {
            Snackbar longSnackbar = Snackbar.make(location, text,
                    Snackbar.LENGTH_LONG);
            ViewGroup longGroup = (ViewGroup) longSnackbar.getView();
            longGroup.setBackgroundColor(ContextCompat.getColor(context, R.color.snackbar));
            longSnackbar.show();

        } else if (duration == 3) {
            Snackbar indefiniteSnackbar = Snackbar.make(location, text,
                    Snackbar.LENGTH_INDEFINITE);
            ViewGroup indefiniteGroup = (ViewGroup) indefiniteSnackbar.getView();
            indefiniteGroup.setBackgroundColor(ContextCompat.getColor(context, R.color.snackbar));
            indefiniteSnackbar.show();
        } else {
            Snackbar indefiniteSnackbar = Snackbar.make(location, text,
                    Snackbar.LENGTH_INDEFINITE);
            ViewGroup indefiniteGroup = (ViewGroup) indefiniteSnackbar.getView();
            indefiniteGroup.setBackgroundColor(ContextCompat.getColor(context, R.color.snackbar));
            indefiniteSnackbar.setDuration(duration);
            indefiniteSnackbar.show();
        }
    }

    public static void sendEmailWithDeviceInfo(Context context) {
        StringBuilder emailBuilder = new StringBuilder();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + context.getResources().getString(R.string.email_id)));
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.email_subject));

        emailBuilder.append("Write here");
        emailBuilder.append("\n \nOS Version: ").append(System.getProperty("os.version")).append("(").append(Build.VERSION.INCREMENTAL).append(")");
        emailBuilder.append("\nOS API Level: ").append(Build.VERSION.SDK_INT);
        emailBuilder.append("\nDevice: ").append(Build.DEVICE);
        emailBuilder.append("\nManufacturer: ").append(Build.MANUFACTURER);
        emailBuilder.append("\nModel (and Product): ").append(Build.MODEL).append(" (").append(Build.PRODUCT).append(")");
        PackageInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        assert appInfo != null;
        emailBuilder.append("\nApp Version Name: ").append(appInfo.versionName);
        emailBuilder.append("\nApp Version Code: ").append(appInfo.versionCode);

        Intent cm = context.getPackageManager().getLaunchIntentForPackage("org.cyanogenmod.theme.chooser");
        Intent cyngn = context.getPackageManager().getLaunchIntentForPackage("com.cyngn.theme.chooser");
        Intent rro = context.getPackageManager().getLaunchIntentForPackage("com.lovejoy777.rroandlayersmanager");
        if (cm != null) {
            emailBuilder.append("\n\nCM theme chooser is installed.");
        }
        if (cyngn != null) {
            emailBuilder.append("\n\nCyanogenOS theme chooser is installed.");
        }
        if (rro != null) {
            emailBuilder.append("\n\nLayers theme engine is installed.");
        }

        intent.putExtra(Intent.EXTRA_TEXT, emailBuilder.toString());
        context.startActivity(Intent.createChooser(intent, (context.getResources().getString(R.string.send_via))));
    }

}
