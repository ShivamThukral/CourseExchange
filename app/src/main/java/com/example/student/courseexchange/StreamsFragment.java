package com.example.student.courseexchange;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
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

class GridViewItem3 {
    public final Drawable icon;       // the drawable for the ListView item ImageView
    public final String title;        // the text for the GridView item title

    public GridViewItem3(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }

}


class GridViewAdapter3 extends BaseAdapter {
    private Context mContext;
    private List<GridViewItem3> mItems;

    public GridViewAdapter3(Context context, List<GridViewItem3> items) {
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
        GridViewItem3 item = mItems.get(position);
        viewHolder.ivIcon.setImageDrawable(item.icon);
        viewHolder.tvTitle.setText(item.title);

        return convertView;
    }

    private static class ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
    }
}


public class StreamsFragment extends Fragment implements AdapterView.OnItemClickListener{


    private List<GridViewItem3> mItems;    // GridView items list
    private GridViewAdapter3 mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* String fontPath = "fonts/OS.ttf";
        TextView txtGhost = (TextView) findViewById(R.id.gvTitle);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        txtGhost.setTypeface(tf);*/

        // initialize the items list
        mItems = new ArrayList<GridViewItem3>();
        Resources resources = getResources();

        mItems.add(new GridViewItem3(resources.getDrawable(R.drawable.letter_m), getString(R.string.action_settings21)));
        mItems.add(new GridViewItem3(resources.getDrawable(R.drawable.letter_s), getString(R.string.action_settings22)));
        mItems.add(new GridViewItem3(resources.getDrawable(R.drawable.letter_i), getString(R.string.action_settings23)));
        mItems.add(new GridViewItem3(resources.getDrawable(R.drawable.letter_t), getString(R.string.action_settings24)));
        mItems.add(new GridViewItem3(resources.getDrawable(R.drawable.letter_d), getString(R.string.action_settings25)));
        mItems.add(new GridViewItem3(resources.getDrawable(R.drawable.letter_c), getString(R.string.action_settings26)));

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
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_streams, container, false);

       /* View fragmentView2 = inflater.inflate(R.layout.fragment_streams, container, false);*/


        // initialize the adapter
        mAdapter = new GridViewAdapter3(getActivity(), mItems);

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
        GridViewItem3 item = mItems.get(position);

        // do something
        Toast.makeText(getActivity(), item.title, Toast.LENGTH_SHORT).show();
    }
    public static StreamsFragment newInstance() {
        StreamsFragment fragment = new StreamsFragment();
        return fragment;

    }


    public StreamsFragment() {
        // Required empty public constructor
    }


}
