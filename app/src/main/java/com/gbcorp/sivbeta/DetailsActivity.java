package com.gbcorp.sivbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

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
    ArrayList<Item> data = new ArrayList<Item>();
    Utils utils = new Utils();
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


        data = utils.fillData();
        Item generateMenuList= new Item();
        //menu = new String[]{"Home","Android","Windows","Linux","Raspberry Pi","WordPress","Videos","Contact Us"};
        ArrayList<String> menulist = new ArrayList<String>();
        for (int i = 0; i < data.size(); i++) {
            generateMenuList=data.get(i);
            menulist.add(generateMenuList.getTitle());
        }
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menulist);

        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_blue_dark);

        dList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {

                dLayout.closeDrawers();
                Item generateMenuListMenu= new Item();
                generateMenuListMenu=data.get(position);
                String selectedFragmentName = generateMenuListMenu.getFragmentName();
                String selectedFragmentTitle = generateMenuListMenu.getTitle();
                getSupportActionBar().setTitle(selectedFragmentTitle);


                FragmentManager fragmentManager  = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                try {
                    Fragment fragment = (Fragment) (Class.forName("com.gbcorp.sivbeta"+selectedFragmentName).newInstance());
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

        });
        FragmentManager fragmentManager  = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        try {
            Fragment fragment = (Fragment) (Class.forName("com.gbcorp.sivbeta"+selectedFragmentName).newInstance());
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
