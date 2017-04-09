package com.example.vinothjoshua.siv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by GBCorp on 4/6/2017.
 */

public class OfficeDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officedashboard);

        LinearLayout admissionBox = (LinearLayout) findViewById(R.id.admissionBox);
        LinearLayout studentBox = (LinearLayout) findViewById(R.id.studentBox);
        LinearLayout staffDataBox = (LinearLayout) findViewById(R.id.staffDataBox);
        LinearLayout feeCounterBox = (LinearLayout) findViewById(R.id.feeCounterBox);
        LinearLayout storeBox = (LinearLayout) findViewById(R.id.storeBox);
        LinearLayout transportBox = (LinearLayout) findViewById(R.id.transportBox);
        LinearLayout hostelBox = (LinearLayout) findViewById(R.id.hostelBox);
        LinearLayout examBox = (LinearLayout) findViewById(R.id.examBox);
        LinearLayout eGovernanceBox = (LinearLayout) findViewById(R.id.eGovernanceBox);
        LinearLayout eventsBox = (LinearLayout) findViewById(R.id.eventsBox);
        LinearLayout circularBox = (LinearLayout) findViewById(R.id.circularBox);

        admissionBox.setOnClickListener(this);
        studentBox.setOnClickListener(this);
        staffDataBox.setOnClickListener(this);
        feeCounterBox.setOnClickListener(this);
        storeBox.setOnClickListener(this);
        transportBox.setOnClickListener(this);
        hostelBox.setOnClickListener(this);
        examBox.setOnClickListener(this);
        eGovernanceBox.setOnClickListener(this);
        eventsBox.setOnClickListener(this);
        circularBox.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.admissionBox:
                Intent admissionIntent = new Intent(this, AdmissionActivity.class);
                startActivity(admissionIntent);
                break;
            case R.id.studentBox:
                Intent studentIntent = new Intent(this, OfficeStudentInfoActivity.class);
                startActivity(studentIntent);
                break;
            case R.id.staffDataBox:
                Intent staffDataIntent = new Intent(this, OfficeStaffInfoActivity.class);
                startActivity(staffDataIntent);
                break;
            case R.id.feeCounterBox:
                Intent feeCounterIntent = new Intent(this, FeeCounterActivity.class);
                startActivity(feeCounterIntent);
                break;
            case R.id.storeBox:
                Intent storeIntent = new Intent(this, StoresActivity.class);
                startActivity(storeIntent);
                break;
            case R.id.transportBox:
                Intent transportIntent = new Intent(this, TransportActivity.class);
                startActivity(transportIntent);
                break;
            case R.id.hostelBox:
                Intent hostelIntent = new Intent(this, HostelActivity.class);
                startActivity(hostelIntent);
                break;
            case R.id.examBox:
                Intent examIntent = new Intent(this, ExamActivity.class);
                startActivity(examIntent);
                break;
            case R.id.eGovernanceBox:
                Intent eGovernanceIntent = new Intent(this, EGovernanceActivity.class);
                startActivity(eGovernanceIntent);
                break;
            case R.id.eventsBox:
                Intent eventsIntent = new Intent(this, EventHandlerActivity.class);
                startActivity(eventsIntent);
                break;
            case R.id.circularBox:
                Intent circularIntent = new Intent(this, CircularsActivity.class);
                startActivity(circularIntent);
                break;
            default:
                break;
        }
    }
}
