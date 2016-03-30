package com.example.student.courseexchange;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class CoreCourses extends ListFragment {
    private List<ListViewItem> mItems;        // ListView items list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // initialize the items list
        mItems = new ArrayList<ListViewItem>();
        Resources resources = getResources();

        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_core_courses), getString(R.string.linear_algebra_txt), getString(R.string.linear_algebra_dsc)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_core_courses), getString(R.string.digital_circuits_txt), getString(R.string.digital_circuits_dsc)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_core_courses), getString(R.string.intro_to_prog_txt), getString(R.string.intro_to_prog_dsc)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_core_courses), getString(R.string.prob_stat_txt), getString(R.string.prob_stat_dsc)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_core_courses), getString(R.string.dsa_txt), getString(R.string.dsa_dsc)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_core_courses), getString(R.string.ied_txt), getString(R.string.ied_dsc)));

        setListAdapter(new ListViewDemoAdapter(getActivity(), mItems));

        super.onCreate(savedInstanceState);

    }


    public static CoreCourses newInstance() {
        CoreCourses fragment = new CoreCourses();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        ListViewItem item = mItems.get(position);

        // do something
        Toast.makeText(getActivity(), item.title, Toast.LENGTH_SHORT).show();
    }

    /*  @Override
      public void onActivityCreated(Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);
          *//*on click listeners*//*
    }
*/
  /*  @Override
    public void onStart() {
        super.onStart();
    }
*/
    public CoreCourses() {
        // Required empty public constructor
    }




}
