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

import com.pitchedapps.material.glass.xposed.utilities.Common;
import com.pitchedapps.material.glass.xposed.utilities.Packages;
import com.pitchedapps.material.glass.xposed.utilities.Preferences;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 7681 on 2016-02-18.
 */

public class ThemePreferences extends PreferenceFragment {

    private Context mContext;
    private Preferences prefs;

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
        prefs = new Preferences(mContext);
        getPreferenceManager().setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        PreferenceScreen prefScreen = getPreferenceManager().createPreferenceScreen(mContext);
        setPreferenceScreen(prefScreen);
        
        prefs.setVersionCode(BuildConfig.VERSION_CODE);

        PreferenceCategory cMain = new PreferenceCategory(mContext);
        cMain.setTitle(R.string.pref_category_main);
        prefScreen.addPreference(cMain);

        initPreferences(cMain);
    }

    private void initPreferences(PreferenceCategory category) {

        List<Packages> packages = Arrays.asList(Packages.values());
        Collections.sort(packages, new Comparator<Packages>() {
            @Override
            public int compare(Packages p1, Packages p2) {
                return p1.getPrefKey().compareTo(p2.getPrefKey());
            }
        });

        for (final Packages p : packages) {
            final CheckBoxPreference pr = new CheckBoxPreference(mContext);
            String desc = "";
            for (String s : p.getDescription()) {
                desc += "\n\u2022 " + s;
            }
            final String message = desc;
            pr.setKey(p.getPrefKey()); //app name
            pr.setTitle(p.toString());
            pr.setOnPreferenceClickListener(new CheckBoxPreference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    pr.setChecked(!pr.isChecked()); //revert pref click
                    new AlertDialog.Builder(getActivity())
                            .setTitle(p.toString())
                            .setMessage(message) //app dialog description
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
