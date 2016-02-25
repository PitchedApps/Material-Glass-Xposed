package com.pitchedapps.material.glass.xposed;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

/**
 * Created by 7681 on 2016-02-22.
 */
public class Intro extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setFullscreen(false);

        super.onCreate(savedInstanceState);

        /* Enable/disable skip button */
        setSkipEnabled(false);

        /* Enable/disable finish button */
        setFinishEnabled(false);

        addSlide(new SimpleSlide.Builder()
                .title(R.string.slide_title_1)
                .description(R.string.slide_description_1)
                .image(R.color.background)
                .background(R.color.slide_background_1)
                .backgroundDark(R.color.slide_background_dark_1)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title(R.string.slide_title_1)
                .description(R.string.slide_description_1)
                .image(R.color.background)
                .background(R.color.slide_background_1)
                .backgroundDark(R.color.slide_background_dark_1)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title(R.string.slide_title_1)
                .description(R.string.slide_description_1)
                .image(R.color.background)
                .background(R.color.slide_background_1)
                .backgroundDark(R.color.slide_background_dark_1)
                .build());
    }
}
