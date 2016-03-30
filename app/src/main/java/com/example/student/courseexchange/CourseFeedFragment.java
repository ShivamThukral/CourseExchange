package com.example.student.courseexchange;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class CourseFeedFragment extends ListFragment {

    private List<ListViewItem> mItems;        // ListView items list

    public static CourseFeedFragment newInstance() {
        CourseFeedFragment fragment = new CourseFeedFragment();
        return fragment;
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

    public CourseFeedFragment() {
        //this.mCommentList = mCommentListTemp;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mItems = new ArrayList<ListViewItem>();
        Resources resources = getResources();

        Iterator<HashMap<String, String>> iterator = MainActivity.mCommentList.iterator();
        while (iterator.hasNext()) {
            HashMap<String, String > hm = iterator.next();
            Iterator it = hm.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
                mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_letter_m), pair.getKey().toString(), pair.getValue().toString()));
                it.remove();
            }
        }

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
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_course_feed, container, false);
//    }

//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//    }


}
