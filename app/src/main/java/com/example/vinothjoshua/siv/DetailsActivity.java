package com.example.vinothjoshua.siv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.io.IOException;

import static android.R.attr.fragment;

/**
 * Created by Seetha on 27-May-17.
 */

public class DetailsActivity extends FragmentActivity {
    String selectedFragmentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //hide Title Bar
//        getActionBar().setTitle("Test");
//        getActionBar().show();

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("selectedFragmentName")!=null){
            selectedFragmentName = bundle.getString("selectedFragmentName");
        }




        FragmentManager fragmentManager  = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        try {
            Fragment fragment = (Fragment) (Class.forName("com.example.vinothjoshua.siv"+selectedFragmentName).newInstance());
//        AdmissionCounsellingFragment fragment = new AdmissionCounsellingFragment();
            fragmentTransaction.replace(R.id.details_fragment, fragment);
            fragmentTransaction.commit();
        }
        catch (ClassNotFoundException e){

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

}
