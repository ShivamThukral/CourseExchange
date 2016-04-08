package com.example.student.courseexchange;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivam thukral on 4/7/2016.
 */
public class AvailableCourses extends ListFragment {

    private List<ListViewItem> mItems;        // ListView items list

    public static AvailableCourses newInstance() {
        AvailableCourses fragment = new AvailableCourses();
        return fragment;
    }

    public AvailableCourses() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mItems = new ArrayList<ListViewItem>();
        Resources resources = getResources();

        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_letter_m), getString(R.string.ac_appliedCrypto_txt), getString(R.string.ac_appliedCrypto_dsc)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_letter_m), getString(R.string.ac_positivePsychology_txt), getString(R.string.ac_positivePsychology_dsc)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_letter_m), getString(R.string.ac_numberThoery_txt), getString(R.string.ac_numberThoery_dsc)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_letter_m), getString(R.string.ac_numberThoery1_txt), getString(R.string.ac_numberThoery1_dsc)));
        setListAdapter(new ListViewDemoAdapter(getActivity(), mItems));

        super.onCreate(savedInstanceState);


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

        ArrayList<String> course = new ArrayList<>();
        course.add(v.getContext().getString(R.string.ac_appliedCrypto_txt));
        course.add(v.getContext().getString(R.string.ac_positivePsychology_txt));
        course.add(v.getContext().getString(R.string.ac_numberThoery_txt));
        course.add(v.getContext().getString(R.string.ac_numberThoery1_txt));

        ArrayList<String> prof = new ArrayList<>();
        prof.add(v.getContext().getString(R.string.ac_appliedCrypto_dsc));
        prof.add(v.getContext().getString(R.string.ac_positivePsychology_dsc));
        prof.add(v.getContext().getString(R.string.ac_numberThoery_dsc));
        prof.add(v.getContext().getString(R.string.ac_numberThoery1_dsc));

        MainActivity.COURSENAME = course.get(position);
        String courseDesc = prof.get(position);
        MainActivity.PROFNAME = courseDesc.substring(courseDesc.indexOf('(')+1, courseDesc.indexOf(')'));
        Log.e("COURSE", MainActivity.COURSENAME);
        Log.e("PROF", MainActivity.PROFNAME);
        Toast.makeText(getActivity(), item.title, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
