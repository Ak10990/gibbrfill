package com.android.gibbrfill;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Akanksha on 12/5/16
 */
public class FilledFragment extends Fragment {

    private Spanned result;

    public FilledFragment() {
    }

    public static FilledFragment getInstance() {
        return new FilledFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        result = ((MainActivity) getActivity()).getResult();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filled, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        TextView filledTextview = (TextView) rootView.findViewById(R.id.filled_textview);
        filledTextview.setText(result);
    }

}