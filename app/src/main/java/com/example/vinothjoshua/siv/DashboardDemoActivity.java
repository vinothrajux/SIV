package com.example.vinothjoshua.siv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * Created by Vinoth Joshua on 09-Apr-17.
 */

public class DashboardDemoActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboarddemo);

        Button principalDashboardBtn = (Button) findViewById(R.id.principalDashboard);
        Button officeDashboardBtn = (Button) findViewById(R.id.officeDashboard);
        Button deptStaffBtn = (Button) findViewById(R.id.deptStaffDashboard);
        Button managementBtn = (Button) findViewById(R.id.managementDashboard);
        Button placementBtn = (Button) findViewById(R.id.placementDashboard);
        Button sportsBtn = (Button) findViewById(R.id.sportsDashboard);

        principalDashboardBtn.setOnClickListener(this);
        officeDashboardBtn.setOnClickListener(this);
        deptStaffBtn.setOnClickListener(this);
        managementBtn.setOnClickListener(this);
        placementBtn.setOnClickListener(this);
        sportsBtn.setOnClickListener(this);




    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.principalDashboard:
                Intent intent1 = new Intent(this, PrincipalDashBoardActivity.class);
                startActivity(intent1);
                break;
            case R.id.officeDashboard:
                Intent intent2 = new Intent(this, DashboardActivity.class);
                startActivity(intent2);
                break;
            case R.id.deptStaffDashboard:
                Intent intent3 = new Intent(this, DeptStaffActivity.class);
                startActivity(intent3);
                break;
            case R.id.managementDashboard:
                Intent intent4 = new Intent(this, ManagementActivity.class);
                startActivity(intent4);
                break;
            case R.id.placementDashboard:
                Intent intent5 = new Intent(this, PlacementActivity.class);
                startActivity(intent5);
                break;
            case R.id.sportsDashboard:
                Intent intent6 = new Intent(this, SportsActivity.class);
                startActivity(intent6);
                break;
            default:
                break;
        }
    }

}
