package com.example.vinothjoshua.siv;

import android.app.Application;

/**
 * Created by VinothJoshua on 7/14/2017.
 */

public final  class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/Roboto-Light.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Roboto-Light.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/Roboto-Light.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/Roboto-Light.ttf");
    }
}
