package com.example.vinothjoshua.siv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static android.R.attr.fragment;

/**
 * Created by Seetha on 27-May-17.
 */

public class DetailsActivity extends AppCompatActivity {
    String selectedFragmentName;
    String selectedFragmentTitle;
    DrawerLayout dLayout;
    ListView dList;
    ArrayAdapter<String> adapter;
    private DrawerLayout mDrawerLayout;
    String[] menu;
    private ActionBarDrawerToggle mDrawerToggle;
    public DetailsActivity() {
        // Required empty public constructor
    }
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
        if(bundle.getString("selectedFragmentTitle")!=null){
            selectedFragmentTitle = bundle.getString("selectedFragmentTitle");
        }
        initViews();


        //menu = new String[]{"Home","Android","Windows","Linux","Raspberry Pi","WordPress","Videos","Contact Us"};
        ArrayList<String> menulist = new ArrayList<String>();
        menulist.add("test1");
        menulist.add("test2");
        menulist.add("test3");
        menulist.add("test4");
        menulist.add("test5");
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menulist);

        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_blue_dark);

        dList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {

                dLayout.closeDrawers();

            }

        });
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
    private void initViews(){

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


//        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
//        setSupportActionBar(toolbar);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout ,  R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };


        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //Log.e("getTitle:",selectedFragmentTitle);
        Toast.makeText(getApplicationContext(), "selectedFragmentTitle: " + selectedFragmentTitle, Toast.LENGTH_SHORT).show();
        getSupportActionBar().setTitle(selectedFragmentTitle);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
