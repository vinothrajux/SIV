package com.example.vinothjoshua.siv;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vinoth Joshua on 15-Apr-17.
 */

public class OfficeDashboardFragment extends Fragment {
    @Override

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


          //Inflate the layout for this fragment

        return inflater.inflate(

                R.layout.activity_officedashboard, container, false);

    }
}
