package com.gbcorp.sivbeta;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Seetha on 04-Apr-18.
 */

public class InstituteSplashScreenActivity extends AppCompatActivity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    ImageView institutelogoimg;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_splash_screen);
        institutelogoimg = (ImageView) findViewById(R.id.splashscreen);
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        Utils utils = new Utils();
        JSONObject userDataObj = new JSONObject();
        userDataObj=utils.getUserData();
        String logoURL = null;
        try {
            logoURL = userDataObj.getString("instituteLogo");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        URL url = null;
        try {
            url = new URL(logoURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        Glide.with(InstituteSplashScreenActivity.this).load(url).into(institutelogoimg);
        Glide.with(InstituteSplashScreenActivity.this).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                progressBar.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                        Intent mainIntent = new Intent(InstituteSplashScreenActivity.this,DashboardActivity.class);
                        InstituteSplashScreenActivity.this.startActivity(mainIntent);
                        InstituteSplashScreenActivity.this.finish();
                    }
                }, SPLASH_DISPLAY_LENGTH);
                return false;
            }
        }).into(institutelogoimg);



    }
}
