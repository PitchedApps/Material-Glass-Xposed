package com.pitchedapps.material.glass.xposed;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pitchedapps.material.glass.xposed.adapters.ChangelogAdapter;
import com.pitchedapps.material.glass.xposed.utilities.Common;
import com.pitchedapps.material.glass.xposed.utilities.Utils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String MARKET_URL = "https://play.google.com/store/apps/details?id=";
    private ComponentName componentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] themeListMain = this.getResources().getStringArray(R.array.theme_list_main);
        String[] themeListLayers = this.getResources().getStringArray(R.array.theme_list_layers);

        componentName = new ComponentName(this, HomeActivity.class);

//        if(isLauncherIconVisible(componentName)) {
//            Intent intent = new Intent(MainActivity.this, Intro.class);
//            startActivity(intent);
//        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        if (isModuleActivated()) {
            ThemePreferences themePrefs = ThemePreferences.newInstance(themeListMain, themeListLayers, isLauncherIconVisible(componentName));
            getFragmentManager().beginTransaction().replace(R.id.container, themePrefs).commit();
//            if (!arePrefsWorking()) {
//                Utils.showSimpleSnackbar(this, findViewById(R.id.main_activity), "Prefs are not working; everything is enabled by default.", 10000);
//            }
        } else {
            Utils.showSimpleSnackbar(this, findViewById(R.id.main_activity), "Module is not enabled.", 3);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.rate:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_URL + Utils.getAppPackageName(this)));
                startActivity(browserIntent);
                break;

            case R.id.send_email:
                Utils.sendEmailWithDeviceInfo(this);
                break;

            case R.id.changelog:
                showChangelog();
                break;
        }
        return true;
    }

    private void showChangelog() {
        new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                .setTitle(R.string.changelog_dialog_title)
                .setAdapter(new ChangelogAdapter(this, R.array.fullchangelog), null)
                    .setPositiveButton(R.string.nice, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                .show();
    }

    private boolean isLauncherIconVisible(ComponentName componentName) {
        int enabledSetting = getPackageManager()
                .getComponentEnabledSetting(componentName);
        return enabledSetting != PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
    }

    public void removeLauncherIcon() {
        PackageManager p = this.getPackageManager();
        p.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        Utils.showSimpleSnackbar(this, findViewById(R.id.main_activity), getResources().getString(R.string.hidden_app), 5000);

    }

    //The following boolean implementations were taken from unbounce, another xposed module for battery saving

    public boolean isModuleActivated() {
        //The module will change this to true.
        return false;
    }

    public boolean arePrefsWorking() {
        //The module will change this to false if it isn't.
        return true;
    }

    public boolean isXposedRunning() {
//        return true;
        return new File("/data/data/de.robv.android.xposed.installer/bin/XposedBridge.jar").exists();
    }

    private boolean isXposedInstalled() {

        PackageManager pm = getPackageManager();

        try {
            pm.getPackageInfo("de.robv.android.xposed.installer", PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private boolean isInstalledFromPlay() {
        String installer = getPackageManager().getInstallerPackageName(Common.PACKAGE_NAME);

        if (installer == null) {
            return false;
        }
        else {
            return installer.equals("com.android.vending");
        }
    }
}
