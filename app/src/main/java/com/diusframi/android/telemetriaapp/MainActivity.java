package com.diusframi.android.telemetriaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.diusframi.android.androidstatserviceapi.DandroidStats;
import com.diusframi.android.telemetriaapp.ui.Fragment1;
import com.diusframi.android.telemetriaapp.ui.Fragment2;
import com.diusframi.android.telemetriaapp.ui.Utils;
import com.google.android.material.navigation.NavigationView;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private  ProgressDialog dialog;

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;




    private void setupView(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mDrawer = findViewById(R.id.drawer_layout);
        nvDrawer = findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return false;
            }
        });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = Fragment1.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = Fragment2.class;
                break;
            default:
                fragmentClass = Fragment1.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupView();
    }

    DandroidStats.DandroidStatsDelegate dandroidStatsDelegate = new DandroidStats.DandroidStatsDelegate() {
        @Override
        public void onInit(boolean status) {
            if (status){
                dialog.dismiss();
                String result = DandroidStats.getInstance().getLogInfo();
                try{
                    JSONArray jac = new JSONArray(result);
                   for (int i =0; i<jac.length(); i++){
                        JSONObject joc =  (JSONObject)jac.get(i);
                        System.out.println("----joc: "+joc.toString());
                   }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onConnectionLost() {
            Toast.makeText(getApplicationContext(),
                    "Connection to stats service lost", Toast.LENGTH_LONG).show();
        }
    };

    private void init(){
        dialog = Utils.showDialog(MainActivity.this,"Please wait... ");
        DandroidStats.getInstance().init(MainActivity.this,dandroidStatsDelegate);

    }



}