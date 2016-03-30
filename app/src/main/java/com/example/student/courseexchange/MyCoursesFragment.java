package com.example.student.courseexchange;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class GridViewItem {
    public final Drawable icon;       // the drawable for the ListView item ImageView
    public final String title;        // the text for the GridView item title

    public GridViewItem(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }
}


 class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<GridViewItem> mItems;

    public GridViewAdapter(Context context, List<GridViewItem> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.gridview_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.gvIcon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.gvTitle);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        GridViewItem item = mItems.get(position);
        viewHolder.ivIcon.setImageDrawable(item.icon);
        viewHolder.tvTitle.setText(item.title);

        return convertView;
    }

    private static class ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
    }
}



public class MyCoursesFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<GridViewItem> mItems;    // GridView items list
    private GridViewAdapter mAdapter;    // GridView adapter



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<GridViewItem>();
        Resources resources = getResources();

        mItems.add(new GridViewItem(resources.getDrawable(R.drawable.ic_monsoon_13), getString(R.string.action_settings1)));
        mItems.add(new GridViewItem(resources.getDrawable(R.drawable.ic_monsoon_14), getString(R.string.action_settings2)));
        mItems.add(new GridViewItem(resources.getDrawable(R.drawable.ic_summer_14), getString(R.string.action_settings3)));
        mItems.add(new GridViewItem(resources.getDrawable(R.drawable.ic_winter_13), getString(R.string.action_settings4)));
        mItems.add(new GridViewItem(resources.getDrawable(R.drawable.ic_winter_14), getString(R.string.action_settings5)));
        mItems.add(new GridViewItem(resources.getDrawable(R.drawable.ic_winter_15), getString(R.string.action_settings6)));

        // mItems.add(new GridViewItem(resources.getDrawable(R.drawable.youtube), getString(R.string.youtube)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the root view of the fragment
        View fragmentView = inflater.inflate(R.layout.fragment_my_courses, container, false);

        // initialize the adapter
        mAdapter = new GridViewAdapter(getActivity(), mItems);

        // initialize the GridView
        GridView gridView = (GridView) fragmentView.findViewById(R.id.gridView);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(this);

        return fragmentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // retrieve the GridView item
        GridViewItem item = mItems.get(position);

        // do something
        Toast.makeText(getActivity(), item.title, Toast.LENGTH_SHORT).show();
        if(position==0)
        {
            // Create new fragment and transaction
            ListFragment newFragment = new BlankFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();
        }
        else
        {
            Log.i("LOGGING", "poisition" + position);
        }
    }

    public static MyCoursesFragment newInstance() {
        MyCoursesFragment fragment = new MyCoursesFragment();
        return fragment;
    }

    public MyCoursesFragment() {
        // Required empty public constructor
    }


    /*
    public static MyCoursesFragment newInstance() {
        MyCoursesFragment fragment = new MyCoursesFragment();
        return fragment;
    }

    public MyCoursesFragment() {
        // Required empty public constructor
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        *//*on click listeners*//*
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_courses, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

*/
}
