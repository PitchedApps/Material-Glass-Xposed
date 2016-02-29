package com.pitchedapps.material.glass.xposed;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;

import com.pitchedapps.material.glass.xposed.utilities.Common;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by 7681 on 2016-02-18.
 */

public class ThemePreferences extends PreferenceFragment {

    public Context context;
    static String[][] themeListMain, themeListLayers;
    public SharedPreferences prefs;
    private static boolean launcherApp;
    private PreferenceScreen prefScreen;

    public static ThemePreferences newInstance(String[] listMain, String[] listLayers, boolean launcherApp2) {
        ThemePreferences themePrefs = new ThemePreferences();
		themeListMain = convertList(listMain);
		themeListLayers = convertList(listLayers);
        launcherApp = launcherApp2;
        return themePrefs;
    }

    public static String[][] convertList(String[] list) {
        String[][] themeList = new String[list.length/2][2];
        for(int i = 0; i < list.length/2; i++) {
            themeList[i][0] = list[2 * i];
            themeList[i][1] = list[2 * i + 1];
        }
        Arrays.sort(themeList, new Comparator<String[]>() {
            public int compare(String[] s1, String[] s2) {
                return s1[0].compareTo(s2[0]);
            }
        });
        return themeList;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity().getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        getPreferenceManager().setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefScreen = getPreferenceManager().createPreferenceScreen(context);
        setPreferenceScreen(prefScreen);

        //master toggle
        final SwitchPreference mt = new SwitchPreference(context);
        mt.setKey(Common.MASTER_TOGGLE);
        mt.setDefaultValue(true);
        mt.setTitle("Master Toggle");
        mt.setSummary("Toggle this module on the fly.");
        mt.setOnPreferenceClickListener(new SwitchPreference.OnPreferenceClickListener() {
            @Override //remove icon if it hasn't been already
            public boolean onPreferenceClick(Preference preference) {
                if (launcherApp) {
                    if (mt.isChecked()) {
                        ((MainActivity) getActivity()).removeLauncherIcon();
                        launcherApp = false;
                    }
                }
                return false;
            }
        });
        prefScreen.addPreference(mt);

        PreferenceCategory cMain = new PreferenceCategory(context);
        cMain.setTitle(R.string.pref_category_main);
        prefScreen.addPreference(cMain);
        cMain.setDependency(Common.MASTER_TOGGLE);

        initPreferences(themeListMain, "", cMain);

        PreferenceCategory cLayers = new PreferenceCategory(context);
        cLayers.setTitle(R.string.pref_category_layers);
        prefScreen.addPreference(cLayers);
        cLayers.setDependency(Common.MASTER_TOGGLE);

        initPreferences(themeListLayers, "_layers", cLayers);

        Common.log("Preferences: " + prefs.getAll());
    }

    @Override
    public void onPause() {
        super.onPause();

        // Set preferences file permissions to be world readable
        File prefsDir = new File(getActivity().getApplicationInfo().dataDir, "shared_prefs");
        File prefsFile = new File(prefsDir, getPreferenceManager().getSharedPreferencesName() + ".xml");
        if (prefsFile.exists()) {
            prefsFile.setReadable(true, false);
        }
        Common.log(prefsFile.getAbsolutePath());
    }

    private void initPreferences(final String[][] list, String prefix, PreferenceCategory category) {

        for(int i = 0; i < list.length; i++) {

            final int finalI = i;
            final CheckBoxPreference pr = new CheckBoxPreference(context);
            pr.setKey(list[finalI][0] + prefix); //app name
            pr.setTitle(list[finalI][0]);
            pr.setOnPreferenceClickListener(new CheckBoxPreference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (pr.isChecked()) { //revert on click change for dialog
                        pr.setChecked(false);
                    } else {
                        pr.setChecked(true);
                    }
                    new AlertDialog.Builder(getActivity())
                            .setTitle(list[finalI][0])
                            .setMessage("\u2022 " + list[finalI][1].replaceAll("/", "\n\u2022")) //app dialog description
                            .setPositiveButton(R.string.enable, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    pr.setChecked(true);
                                    Common.log("New Preferences: " + prefs.getAll());
                                }
                            })
                            .setNegativeButton(R.string.disable, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    pr.setChecked(false);
                                    Common.log("New Preferences: " + prefs.getAll());
                                }
                            })
                            .show();
                    return false;
                }
            });

            category.addPreference(pr);
        }

    }

}
