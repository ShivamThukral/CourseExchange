package com.example.student.courseexchange;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MobileComputingStream extends ListFragment {
    private List<ListViewItem> mItems;        // ListView items list

    public static MobileComputingStream newInstance() {
        MobileComputingStream fragment = new MobileComputingStream();
        return fragment;
    }

    public MobileComputingStream() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mItems = new ArrayList<ListViewItem>();
        Resources resources = getResources();

        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_letter_m), getString(R.string.mc_txt), getString(R.string.mc_dsc)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_letter_m), getString(R.string.wn_txt), getString(R.string.wn_dsc)));
        mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_letter_m), getString(R.string.an_txt), getString(R.string.an_dsc)));

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

        // do something
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
