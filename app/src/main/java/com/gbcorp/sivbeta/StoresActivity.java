package com.gbcorp.sivbeta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Seetha on 08-Apr-17.
 */

public class StoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.stores);

        //hide Title Bar
        getSupportActionBar().hide();



    }
}
