package com.example.student.courseexchange;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends AppCompatActivity {


    private CharSequence mTitle;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    Fragment fragment = new BlankFragment();
    ActionBarDrawerToggle drawerToggle;

    JSONParser jsonParser = new JSONParser();

    private static final String ADD_FEEDBACK_URL = "http://192.168.49.231/webservice/add_feedback.php";
    private static final String READ_FEEDBACK_URL = "http://192.168.49.231/webservice/read_feedback.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    private static final String TAG_UPVOTE = "upvote";
    private static final String TAG_DOWNVOTE = "downvote";
    private static final String TAG_NAME = "name";
    private static final String TAG_REVIEW = "review";
    private static final String TAG_POSTS = "posts";

    private JSONArray mComments = null;
    public static ArrayList<HashMap<String, String>> mCommentList = new ArrayList<>();
    public static String COURSENAME = "Applied Cryptography", PROFNAME = "Somitra Sanadhya";
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

        new insertCourseSpecs().execute();

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
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
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
        switch (menuItem.getItemId()) {
            case R.id.nav_myCourses:
                fragment = MyCoursesFragment.newInstance();
                break;
            case R.id.nav_allCourses:
                fragment = AllCoursesFragment.newInstance();
                break;
            case R.id.nav_courseFeed: {
                insertCourseSpecs ics = new insertCourseSpecs();
                ics.setCourse(COURSENAME);
                ics.setProf(PROFNAME);
                new insertCourseSpecs().execute();
                fragment = CourseFeedFragment.newInstance();
            }
            break;
            case R.id.nav_aboutUs:
                fragment = AboutUsFragment.newInstance();
                break;
            case R.id.nav_help:
                fragment = HelpFragment.newInstance();
                break;
            case R.id.nav_discover:
                fragment = DiscoverFragment.newInstance();
                break;
            case R.id.nav_feedback:
                fragment = FeedbackFragment.newInstance();
                break;
            case R.id.nav_Streams:
                fragment = StreamsFragment.newInstance();
                // fragment = new BlankFragment();
                break;
            default:
                fragment = new BlankFragment();
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


    public void readFeed(View view, String course, String prof) {
        insertCourseSpecs ics = new insertCourseSpecs();
        ics.setCourse(course);
        ics.setProf(prof);
        Log.i("TESTING",course+prof);
        System.out.println("Course0: " + course);
        System.out.println("Prof0: "+prof);
        ics.execute();

        //Log.i("TESTING",course+prof);
        //System.out.println("Course0: " + course);
        //System.out.println("Prof0: "+prof);
    }

    class insertCourseSpecs extends AsyncTask<String, String, String> {

        String course;
        String prof;

        public void setCourse(String course){
            this.course = course;
        }

        public void setProf(String prof){
            this.course = prof;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... args) {

            int success;

            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                System.out.println("Course: "+course);
                System.out.println("Prof: "+prof);
                params.add(new BasicNameValuePair("coursename", COURSENAME));
                params.add(new BasicNameValuePair("instructor", PROFNAME));

                Log.d("request!", "starting");

                JSONObject json = jsonParser.makeHttpRequest(READ_FEEDBACK_URL, "POST", params);

                Log.d("Login attempt", json.toString());

                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Login Successful!", json.toString());

                    mComments = json.getJSONArray(TAG_POSTS);
                    Log.d("Login Successful!", mComments.toString());


                    try {

                        for (int i = 0; i < mComments.length(); i++) {
                            JSONObject c = mComments.getJSONObject(i);

                            String name = c.getString(TAG_NAME);
                            String upvote = c.getString(TAG_UPVOTE);
                            String downvote = c.getString(TAG_DOWNVOTE);
                            String review = c.getString(TAG_REVIEW);

                            HashMap<String, String> map = new HashMap<String, String>();

                            map.put(name, review);

                            mCommentList.add(map);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {

        }

    }

    String feedBack, grade, load, heavy;
    String rollNumber = LoginActivity.rollNumber;

    public void submitFeed(View view) {
        EditText et = (EditText) findViewById(R.id.feedbackEditText);
        feedBack = et.getText().toString();

        Spinner lsp = (Spinner) findViewById(R.id.loadSpinner);
        load = lsp.getSelectedItem().toString();

        Spinner gsp = (Spinner) findViewById(R.id.avGradeSpinner);
        grade = gsp.getSelectedItem().toString();

        RadioButton rb = (RadioButton) findViewById(R.id.yesProjectButton);
        if (rb.isSelected()) {
            heavy = "1";
        } else {
            heavy = "0";
        }

        new insertFeedback().execute();

        //new insertCourseSpecs().execute();
        //new insertCourseSpecs().execute();
        //for (int i = 0; i < 2; i++) {
            readFeed(view, "Applied Cryptography", "Somitra Sanadhya");


            fragment = CourseFeedFragment.newInstance();
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

            // Highlight the selected item has been done by NavigationView

            // Set action bar title
            setTitle("Reviews");

            //readFeed(view);
            // Close the navigation drawer
            //mDrawer.closeDrawers();
        //}

    }

    class insertFeedback extends AsyncTask<String, String, String> {

        //three methods get called, first preExecture, then do in background, and once do
        //in back ground is completed, the onPost execture method will be called.

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {

            int success;

            try {

                List<NameValuePair> params = new ArrayList<>();

                params.add(new BasicNameValuePair("cid", "1"));
                params.add(new BasicNameValuePair("rnum", rollNumber));
                params.add(new BasicNameValuePair("review", feedBack));
                params.add(new BasicNameValuePair("grade", grade));
                params.add(new BasicNameValuePair("wload", load));
                params.add(new BasicNameValuePair("prj", heavy));
                params.add(new BasicNameValuePair("up", "1"));
                params.add(new BasicNameValuePair("down", "0"));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(ADD_FEEDBACK_URL, "POST", params);

                // check your log for json response
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {

        }

    }


}
