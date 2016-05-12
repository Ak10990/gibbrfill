package com.android.gibbrfill;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Akanksha on 12/5/16
 */
public class GetStartedFragment extends Fragment implements View.OnClickListener {

    private ActivityFragmentCallback callback;

    public GetStartedFragment() {
    }

    public static GetStartedFragment getInstance() {
        return new GetStartedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_get_started, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        Button getStartedBtn = (Button) rootView.findViewById(R.id.get_started_btn);
        getStartedBtn.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            callback = (ActivityFragmentCallback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_started_btn:
                callback.onGetStarted();
                break;
        }
    }
}
