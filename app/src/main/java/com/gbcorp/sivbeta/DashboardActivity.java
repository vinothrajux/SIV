package com.gbcorp.sivbeta;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
//import org.eazegraph.lib.R;

/**
 * Created by Vinoth Joshua on 03-Apr-17.
 */

public class DashboardActivity extends AppCompatActivity {

    ArrayList<Item> data = new ArrayList<Item>();
    GridView grid;
    String userCategory = "";
    String[] dashboardColors = {
            "#2DA3AD",
            "#EC538D",
            "#77C046",
            "#FC5E13",
            "#EDA11B",
            "#E4444B"
    } ;


    String userRole;
    String[] menu;
    DrawerLayout dLayout;
    ListView dList;
    Utils utils = new Utils();
    ArrayAdapter<String> adapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    public DashboardActivity() {
        // Required empty public constructor
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initViews();
//        Bundle bundle = getIntent().getExtras();
//        if(bundle.getString("userRole")!=null){
//            userRole = bundle.getString("userRole");
//        }
        userRole = utils.getUserRole();
        data = utils.fillData();


        //menu = new String[]{"Home","Android","Windows","Linux","Raspberry Pi","WordPress","Videos","Contact Us"};
        Item generateMenuList= new Item();

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
//                Bundle args = new Bundle();
//                args.putString("Menu", menu[position]);
//                Fragment detail = new DetailFragment();
//                detail.setArguments(args);
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
                Item generateMenuListMenu= new Item();
                generateMenuListMenu=data.get(position);
                String selectedFragmentName = generateMenuListMenu.getFragmentName();
                String selectedFragmentTitle = generateMenuListMenu.getTitle();
                Intent intent = new Intent(DashboardActivity.this,DetailsActivity.class);
                intent.putExtra("selectedFragmentName", selectedFragmentName);
                intent.putExtra("selectedFragmentTitle", selectedFragmentTitle);
                startActivity(intent);

            }

        });







        DashboardGrid adapter = new DashboardGrid(DashboardActivity.this,R.layout.gridview_dashboardtool_single ,data , dashboardColors);
        grid=(GridView)findViewById(R.id.dashboardTools);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String selectedDetail="";
            String selectedFragmentName;
            String selectedFragmentTitle;
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item selectedDashboardTool= new Item();
                selectedDashboardTool=data.get(position);
                selectedFragmentName = selectedDashboardTool.getFragmentName();
                selectedFragmentTitle = selectedDashboardTool.getTitle();
                Intent intent = new Intent(DashboardActivity.this,DetailsActivity.class);
                intent.putExtra("selectedFragmentName", selectedFragmentName);
                intent.putExtra("selectedFragmentTitle", selectedFragmentTitle);
                startActivity(intent);
            }
        });
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

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
