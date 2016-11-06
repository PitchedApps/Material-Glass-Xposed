package com.pitchedapps.material.glass.xposed.themes;

import android.content.res.ColorStateList;
import android.content.res.XModuleResources;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pitchedapps.material.glass.xposed.R;
import com.pitchedapps.material.glass.xposed.XMaterialGlass;
import com.pitchedapps.material.glass.xposed.utilities.Common;
import com.pitchedapps.material.glass.xposed.utilities.Packages;
import com.pitchedapps.material.glass.xposed.views.ViewType;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by 7681 on 2016-02-19.
 */
public abstract class ThemeBase {

    private HashMap<String, Integer> colorMap, drawableMap;
    private HashMap<String, XC_LayoutInflated> layoutMap;
    private HashMap<String, HashMap<String, ViewType>> viewTree;
    private InitPackageResourcesParam resparam;
    private XModuleResources modRes;
    protected String packageName;
    private XC_LoadPackage.LoadPackageParam lpparam;

    public void handleInitPackageResources(InitPackageResourcesParam resparam) {
        this.resparam = resparam;
        packageName = resparam.packageName;
        modRes = XModuleResources.createInstance(XMaterialGlass.MODULE_PATH, resparam.res);
        handleInitPackageResources();
        loadColors();
        loadDrawables();
        loadLayouts();
    }

    public abstract void handleInitPackageResources();

    protected void addColor(String name, @ColorInt int color) {
        if (colorMap == null) colorMap = new HashMap<>();
        colorMap.put(name, color);
    }

    private void loadColors() {
        if (colorMap == null) return;
        for (String key : colorMap.keySet()) {
            resparam.res.setReplacement(packageName, "color", key, modRes.fwd(colorMap.get(key)));
        }
    }

    protected void addDrawable(String name, @DrawableRes int drawable) {
        if (drawableMap == null) drawableMap = new HashMap<>();
        drawableMap.put(name, drawable);
    }

    private void loadDrawables() {
        if (drawableMap == null) return;
        for (String key : drawableMap.keySet()) {
            try {
                resparam.res.setReplacement(packageName, "drawable", key, modRes.fwd(drawableMap.get(key)));
            } catch (Throwable t) {
                Common.xLog(packageName + " drawable not found: " + key);
            }
        }
    }

    protected void addLayout(String name, XC_LayoutInflated inflation) {
        if (layoutMap == null) layoutMap = new HashMap<>();
        layoutMap.put(name, inflation);
    }

    protected void addLayout(final String layout, final String id, final ViewType viewType) {
        if (viewTree == null) viewTree = new HashMap<>();
        if (viewTree.containsKey(layout)) {
            viewTree.get(layout).put(id, viewType);
        } else {
            viewTree.put(layout, new HashMap<String, ViewType>() {{
                put(id, viewType);
            }});
        }
    }

    private void loadLayouts() {
        if (layoutMap != null) {
            for (String key : layoutMap.keySet()) {
                try {
                    resparam.res.hookLayout(packageName, "layout", key, layoutMap.get(key));
                } catch (Exception e) {
                    Common.xLog(packageName + " " + key + " error " + e);
                }
            }
        }
        if (viewTree != null) {
            for (final String layout : viewTree.keySet()) {
                resparam.res.hookLayout(packageName, "layout", layout, new XC_LayoutInflated() {
                    @Override
                    public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                        HashMap<String, ViewType> viewMap = viewTree.get(layout); //inner hashmap
                        for (final String id : viewMap.keySet()) {
                            View view = liparam.view.findViewById(
                                    liparam.res.getIdentifier(id, "id", packageName));
                            switch (viewMap.get(id)) {
                                case BUTTON:
                                    Button button = (Button) view;
                                    button.setTextColor(ColorStateList.valueOf(Common.TEXT_COLOR));
                                    button.setHintTextColor(ColorStateList.valueOf(Common.TEXT_COLOR_CACHE));
                                    break;
                                case TEXTVIEW:
                                    TextView textView = (TextView) view;
                                    textView.setTextColor(ColorStateList.valueOf(Common.TEXT_COLOR));
                                    textView.setHintTextColor(ColorStateList.valueOf(Common.TEXT_COLOR_CACHE));
                                    break;
                                case EDITTEXT:
                                    EditText editText = (EditText) view;
                                    editText.setTextColor(ColorStateList.valueOf(Common.TEXT_COLOR));
                                    editText.setHintTextColor(ColorStateList.valueOf(Common.TEXT_COLOR_CACHE));
                                    break;
                            }
                        }
                    }
                });
            }
        }
    }

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        this.lpparam = lpparam;
        packageName = lpparam.packageName;
    }

}
