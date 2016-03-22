package com.example.student.courseexchange;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
         {


    private CharSequence mTitle;
             private DrawerLayout mDrawer;
             private Toolbar toolbar;
             private NavigationView nvDrawer;
             Fragment fragment = BlankFragment.newInstance();
             ActionBarDrawerToggle drawerToggle;
             @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                 // Set a Toolbar to replace the ActionBar.
                 toolbar = (Toolbar) findViewById(R.id.toolbar);
                 setSupportActionBar(toolbar);

                 // Find our drawer view
                 mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                 // Find our drawer view
                 nvDrawer = (NavigationView) findViewById(R.id.navigation_drawer);
                 drawerToggle = setupDrawerToggle();

                 // Tie DrawerLayout events to the ActionBarToggle
                 mDrawer.setDrawerListener(drawerToggle);

                 // Setup drawer view
                 setupDrawerContent(nvDrawer);


    }

             @Override
             public void onBackPressed() {
                 if (mDrawer.isDrawerOpen(nvDrawer)) {
                     mDrawer.closeDrawer(nvDrawer);
                 } else {
                     super.onBackPressed();
                 }
             }

             @Override
             public boolean onPrepareOptionsMenu(final Menu menu) {
                 setupDrawerContent(nvDrawer);
                 return super.onPrepareOptionsMenu(menu);
             }
             private ActionBarDrawerToggle setupDrawerToggle() {
                 return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
             }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);

    }



             // `onPostCreate` called when activity start-up is complete after `onStart()`
             // NOTE! Make sure to override the method with only a single `Bundle` argument
             @Override
             protected void onPostCreate(Bundle savedInstanceState) {
                 super.onPostCreate(savedInstanceState);
                 // Sync the toggle state after onRestoreInstanceState has occurred.
                 drawerToggle.syncState();

             }
             private void setupDrawerContent(NavigationView navigationView) {
                 navigationView.setNavigationItemSelectedListener(
                         new NavigationView.OnNavigationItemSelectedListener() {
                             @Override
                             public boolean onNavigationItemSelected(MenuItem menuItem) {
                                 selectDrawerItem(menuItem);
                                 return true;
                             }
                         });
             }

             @Override
             public void onConfigurationChanged(Configuration newConfig) {
                 super.onConfigurationChanged(newConfig);
                 // Pass any configuration change to the drawer toggles
                 drawerToggle.onConfigurationChanged(newConfig);
             }
             public void selectDrawerItem(MenuItem menuItem) {
                 // Create a new fragment and specify the fragment to show based on nav item clicked

                 Class fragmentClass;
                 switch(menuItem.getItemId()) {
                     case R.id.nav_myCourses:
                         //fragmentClass = FirstFragment.class;
                         break;
                     case R.id.nav_allCourses:
                         //fragmentClass = SecondFragment.class;
                         break;
                     case R.id.nav_courseFeed:
                         //fragmentClass = ThirdFragment.class;
                         break;
                     case R.id.nav_aboutUs:
                         //fragmentClass = ThirdFragment.class;
                         break;
                     case R.id.nav_settings:
                         //fragmentClass = ThirdFragment.class;
                         break;
                     case R.id.nav_discover:
                         //fragmentClass = ThirdFragment.class;
                         break;
                     case R.id.nav_Streams:
                         //fragmentClass = ThirdFragment.class;
                         break;

                     default:
                         //fragmentClass = FirstFragment.class;
                 }

                 try {
                     //fragment = (Fragment) fragmentClass.newInstance();
                 } catch (Exception e) {
                     e.printStackTrace();
                 }

                 // Insert the fragment by replacing any existing fragment
                 FragmentManager fragmentManager = getSupportFragmentManager();
                 fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

                 // Highlight the selected item has been done by NavigationView
                 menuItem.setChecked(true);
                 // Set action bar title
                 setTitle(menuItem.getTitle());
                 // Close the navigation drawer
                 mDrawer.closeDrawers();
             }


         }
