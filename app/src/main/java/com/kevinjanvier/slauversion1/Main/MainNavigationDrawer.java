package com.kevinjanvier.slauversion1.Main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.kevinjanvier.slauversion1.Login.Config;
import com.kevinjanvier.slauversion1.Login.LoginActivity;
import com.kevinjanvier.slauversion1.NavigationDrawer.AboutFragment;
import com.kevinjanvier.slauversion1.NavigationDrawer.AdmissionFragment;
import com.kevinjanvier.slauversion1.NavigationDrawer.ContactFragment;
import com.kevinjanvier.slauversion1.NavigationDrawer.HomeFragment;
import com.kevinjanvier.slauversion1.NavigationDrawer.LibraryFragment;
import com.kevinjanvier.slauversion1.NavigationDrawer.MapFragment;
import com.kevinjanvier.slauversion1.NavigationDrawer.NewsEvenFragment;
import com.kevinjanvier.slauversion1.NavigationDrawer.TimeTableFragment;
import com.kevinjanvier.slauversion1.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainNavigationDrawer extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navDrawer;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle drawerToggle;
    Config config;

@Nullable
@Bind(R.id.usernameprofile)
    TextView usernameprofile;

//    private TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_navigation_drawer);

        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Set a Toolbar to replace the ActionBar.

//        usernameprofile = (TextView) findViewById(R.id.usernameprofile);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        textView = (TextView) findViewById(R.id.textView);

        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navDrawer = (NavigationView) findViewById(R.id.nvView);
        drawerToggle = setupDrawerToggle();
        dlDrawer.setDrawerListener(drawerToggle);
        setupDrawerContent(navDrawer);


//
//
//
//        navDrawer.getMenu().getItem(0).setChecked(true);
//
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.flContent, new HomeFragment()).commit();
//        setTitle(R.id.about);

        dlDrawer.setDrawerListener(drawerToggle);
        navDrawer.getMenu().getItem(0).setChecked(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, new HomeFragment()).commit();
        setTitle(R.string.home);

        //Fetching email from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Config.USERNAME_SHARED_PREF, "Not Available");

        //Display a user Details Inside the Navigation Drawer Header

//
//        //showing the current Username
        usernameprofile.setText("username:" +username);



    }

    private void setupDrawerContent(NavigationView navDrawer) {
        navDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
               selectDrawerItem(menuItem);
                return true;
            }
        });
    }
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, dlDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);

    }

    private void selectDrawerItem(MenuItem menuItem){

        Fragment fragment = null;
        Class MyFragmentClass;

        switch (menuItem.getItemId()){
            case R.id.home:
                MyFragmentClass = HomeFragment.class;
                break;
            case R.id.about:
                MyFragmentClass = AboutFragment.class;
                break;
            case R.id.library:
                MyFragmentClass = LibraryFragment.class;
                break;
            case R.id.adminssion:
                MyFragmentClass = AdmissionFragment.class;
                break;
            case R.id.timetable:
                MyFragmentClass = TimeTableFragment.class;
                break;
            case R.id.map:
                MyFragmentClass = MapFragment.class;
                break;
            case R.id.news:
                MyFragmentClass = NewsEvenFragment.class;
                break;
            case R.id.contact:
                MyFragmentClass = ContactFragment.class;
                break;
            default:
                MyFragmentClass = HomeFragment.class;
        }
        try {
             fragment =(Fragment) MyFragmentClass.newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.flContent, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        dlDrawer.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;

        }
        if (id == R.id.menuLogout){
            logoutfinish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logoutfinish() {
        //Create the Dialog for the Logout
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want logout?");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Getting out the Share Preference
                SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                //Getting Editot
                SharedPreferences.Editor editor = preferences.edit();

                //putting the value false for log
                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                //puting black the value
                editor.putString(Config.USERNAME_SHARED_PREF, "");

                //SAVING THE PREFERENCE
                editor.commit();

                //take a user to the login Activity
                Intent loginuserout = new Intent(MainNavigationDrawer.this, LoginActivity.class);
                startActivity(loginuserout);

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
