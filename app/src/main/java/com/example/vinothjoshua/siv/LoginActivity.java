package com.example.vinothjoshua.siv;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonafide);

        //hide Title Bar
        getSupportActionBar().hide();



    }

    public void dashboard(View view){
        Intent intent = new Intent(LoginActivity.this,DashboardDemoActivity.class);
        startActivity(intent);
    }
}
