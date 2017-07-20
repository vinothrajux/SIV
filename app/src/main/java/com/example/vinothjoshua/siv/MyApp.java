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
        FontsOverride.setDefaultFont(this, "ROBOTO_BLACK", "fonts/Roboto-Black.ttf");
        FontsOverride.setDefaultFont(this, "ROBOTO_BOLD", "fonts/Roboto-Bold.ttf");
        FontsOverride.setDefaultFont(this, "ROBOTO_ITALIC", "fonts/Roboto-Italic.ttf");
        FontsOverride.setDefaultFont(this, "ROBOTO_MEDIUM", "fonts/Roboto-Medium.ttf");
        FontsOverride.setDefaultFont(this, "ROBOTO_THIN", "fonts/Roboto-Thin.ttf");
        FontsOverride.setDefaultFont(this, "ROBOTO_LIGHT", "fonts/Roboto-Light.ttf");
    }
}
