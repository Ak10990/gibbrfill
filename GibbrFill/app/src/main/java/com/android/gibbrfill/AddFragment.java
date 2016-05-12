package com.android.gibbrfill;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akanksha on 12/5/16
 */
public class AddFragment extends Fragment implements View.OnClickListener {

    private String[] words;
    private List<FillString> addingList = new ArrayList<>();
    private TextView fillLeftView, typeText;
    private EditText etEnter;
    private int currentAdd = -1;
    private ActivityFragmentCallback callback;

    public AddFragment() {
    }

    public static AddFragment getInstance() {
        return new AddFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initValues();
    }

    private void initValues() {
        words = Constants.GIBBR_FILL_TXT.split(" ");
        for (String word : words) {
            if (word.startsWith("<") && word.endsWith(">")) {
                addingList.add(new FillString(word.substring(1, word.length() - 1)));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        fillLeftView = (TextView) rootView.findViewById(R.id.fill_left_textview);
        typeText = (TextView) rootView.findViewById(R.id.type_text);
        etEnter = (EditText) rootView.findViewById(R.id.et_enter);

        Button okayBtn = (Button) rootView.findViewById(R.id.okay_btn);
        okayBtn.setOnClickListener(this);

        if (addingList.size() > 0) {
            invalidateViews();
        } else {
            Toast.makeText(getActivity(), "Please enter correct input string constants.", Toast.LENGTH_LONG).show();
            onAllFilled();
        }
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

    private void invalidateViews() {
        int size = addingList.size();
        if (currentAdd != -1) {
            addingList.get(currentAdd).setResult(etEnter.getText().toString());
            etEnter.setText("");
        }
        currentAdd++;
        if (currentAdd < size) {
            String fillLeft = (size - currentAdd) + " word(s) left";
            fillLeftView.setText(fillLeft);
            String type = addingList.get(currentAdd).getType();
            etEnter.setHint(type);
            typeText.setText("please type a/an " + type);
        } else {
            onAllFilled();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.okay_btn:
                if (!TextUtils.isEmpty(etEnter.getText().toString())) {
                    invalidateViews();
                } else {
                    Toast.makeText(getActivity(), "Please fill in a word.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void onAllFilled() {
        StringBuilder resultString = new StringBuilder();
        if (addingList.size() > 0) {
            int j = 0;
            for (String word : words) {
                if (word.startsWith("<") && word.endsWith(">")) {
                    String result = addingList.get(j).getResult();
                    resultString.append("<b>").append(" ").append(result).append("</b>");
                    j++;
                } else {
                    resultString.append(" ").append(word);
                }
            }
        }

        callback.onAllFilled(Html.fromHtml(resultString.toString()));
    }
}