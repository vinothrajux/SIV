package com.example.vinothjoshua.siv;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Vinoth Joshua on 03-Apr-17.
 */

public class DashboardActivity extends FragmentActivity {
    GridView grid;
    String userCategory = "principal";
    String[] dashboardColors = {
            "#2DA3AD",
            "#EC538D",
            "#77C046",
            "#FC5E13",
            "#EDA11B",
            "#E4444B"
    } ;

    ArrayList<Item> data = new ArrayList<Item>();
    private void fillData()
    {
        switch(userCategory){
            case "management":
                data.add(new Item("Academic",R.drawable.admission,".CircularsActivity"));
                data.add(new Item("Accounts", R.drawable.accounts,".CircularsActivity"));
                data.add(new Item("Accounts1", R.drawable.accounts1,".CircularsActivity"));
                data.add(new Item("Admission", R.drawable.admission,".CircularsActivity"));
                break;

            case "principal":
                data.add(new Item("principal",R.drawable.admission,".CircularsActivity"));
                data.add(new Item("Accounts", R.drawable.accounts,".CircularsActivity"));
                data.add(new Item("Accounts1", R.drawable.accounts1,".CircularsActivity"));
                data.add(new Item("Admission", R.drawable.admission,".CircularsActivity"));
                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        if (savedInstanceState == null) {
//            OfficeDashboardFragment fr = new OfficeDashboardFragment();
////        if(view == findViewById(R.id.button2)) {
////
////            fr = new FragmentTwo();
////
////        }else {
////
////            fr = new FragmentOne();
////
////        }
//
//            FragmentManager fm = getFragmentManager();
//
//            FragmentTransaction fragmentTransaction = fm.beginTransaction();
//
//            fragmentTransaction.add(R.id.dashBoardFragemntContaier, fr);
//
//            fragmentTransaction.commit();
//        }
        fillData();
        DashboardGrid adapter = new DashboardGrid(DashboardActivity.this,R.layout.gridview_dashboardtool_single ,data , dashboardColors);
        grid=(GridView)findViewById(R.id.dashboardTools);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String testtext;
            String nextActivityClassName;
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item testitem= new Item();
                testitem=data.get(position);
                testtext=testitem.getTitle();
                nextActivityClassName = testitem.getNextActivityClassName();

                Toast.makeText(DashboardActivity.this, "You Clicked at " + testtext, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClassName("com.example.vinothjoshua.siv", "com.example.vinothjoshua.siv"+nextActivityClassName);
                startActivity(intent);
            }
        });
    }

}
