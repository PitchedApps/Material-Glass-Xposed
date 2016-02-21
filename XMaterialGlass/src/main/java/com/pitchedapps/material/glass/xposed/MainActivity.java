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
import com.pitchedapps.material.glass.xposed.utilities.Utils;

public class MainActivity extends AppCompatActivity {

    private static final String MARKET_URL = "https://play.google.com/store/apps/details?id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        String[] themeList = getResources().getStringArray(R.array.theme_list);

        ThemePreferences themePrefs = ThemePreferences.newInstance(themeList);

        //hide app icon
        ComponentName componentName = new ComponentName(this, HomeActivity.class);
        PackageManager p = this.getPackageManager();
        if (isLauncherIconVisible(componentName)) {
            p.setComponentEnabledSetting(componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);

            Utils.showSimpleSnackbar(this, findViewById(R.id.main_activity), getResources().getString(R.string.hidden_app), 5000);
        }


        getFragmentManager().beginTransaction().replace(R.id.container, themePrefs).commit();
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


}
