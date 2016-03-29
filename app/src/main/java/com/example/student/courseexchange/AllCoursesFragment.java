package com.example.student.courseexchange;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

class GridViewItem2 {
    public final Drawable icon;       // the drawable for the ListView item ImageView
    public final String title;        // the text for the GridView item title

    public GridViewItem2(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }
}


class GridViewAdapter2 extends BaseAdapter {
    private Context mContext;
    private List<GridViewItem2> mItems;

    public GridViewAdapter2(Context context, List<GridViewItem2> items) {
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
        GridViewItem2 item = mItems.get(position);
        viewHolder.ivIcon.setImageDrawable(item.icon);
        viewHolder.tvTitle.setText(item.title);

        return convertView;
    }

    private static class ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
    }
}


public class AllCoursesFragment extends Fragment implements AdapterView.OnItemClickListener{

    private List<GridViewItem2> mItems;    // GridView items list
    private GridViewAdapter2 mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<GridViewItem2>();
        Resources resources = getResources();

        mItems.add(new GridViewItem2(resources.getDrawable(R.drawable.ic_core_courses), getString(R.string.action_settings11)));
        mItems.add(new GridViewItem2(resources.getDrawable(R.drawable.ic_hss_courses), getString(R.string.action_settings12)));
        mItems.add(new GridViewItem2(resources.getDrawable(R.drawable.ic_tech_courses), getString(R.string.action_settings13)));
        mItems.add(new GridViewItem2(resources.getDrawable(R.drawable.ic_non_tech_courses), getString(R.string.action_settings14)));
        mItems.add(new GridViewItem2(resources.getDrawable(R.drawable.ic_online_courses), getString(R.string.action_settings15)));
        mItems.add(new GridViewItem2(resources.getDrawable(R.drawable.ic_summer_courses), getString(R.string.action_settings16)));

        // mItems.add(new GridViewItem(resources.getDrawable(R.drawable.youtube), getString(R.string.youtube)));
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*on click listeners*/
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_all_courses, container, false);

        // initialize the adapter
        mAdapter = new GridViewAdapter2(getActivity(), mItems);

        // initialize the GridView
        GridView gridView = (GridView) fragmentView.findViewById(R.id.gridView);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(this);

        return fragmentView;
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GridViewItem2 item = mItems.get(position);

        // do something
        Toast.makeText(getActivity(), item.title, Toast.LENGTH_SHORT).show();
    }
    public static AllCoursesFragment newInstance() {
        AllCoursesFragment fragment = new AllCoursesFragment();
        return fragment;
    }

    public AllCoursesFragment() {
        // Required empty public constructor
    }
}
