package com.example.student.courseexchange;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class FeedbackFragment extends Fragment {
    public FeedbackFragment() {
        // Required empty public constructor
    }

    public static FeedbackFragment newInstance() {
        FeedbackFragment fragment = new FeedbackFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);

        //Populate the spinner in the fragment
        Spinner loadSpinner = (Spinner) rootView.findViewById(R.id.loadSpinner);

        List<String> loadCategories = new ArrayList<String>();
        loadCategories.add("3");
        loadCategories.add("5");
        loadCategories.add("7");
        loadCategories.add("9");
        loadCategories.add("11");
        loadCategories.add("13");
        loadCategories.add("15");
        loadCategories.add("18");

        ArrayAdapter<String> loadDataAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, loadCategories);
        loadDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        loadSpinner.setAdapter(loadDataAdapter);

        Spinner gradeSpinner = (Spinner) rootView.findViewById(R.id.avGradeSpinner);

        List<String> gradeCategories = new ArrayList<String>();
        gradeCategories.add("4");
        gradeCategories.add("5");
        gradeCategories.add("6");
        gradeCategories.add("7");
        gradeCategories.add("8");
        gradeCategories.add("9");
        gradeCategories.add("10");

        ArrayAdapter<String> gradeDataAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, gradeCategories);
        gradeDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        gradeSpinner.setAdapter(gradeDataAdapter);

        return rootView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
