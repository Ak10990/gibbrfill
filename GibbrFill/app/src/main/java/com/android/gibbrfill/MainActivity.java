package com.android.gibbrfill;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;

/**
 * Created by Akanksha on 12/5/16
 */
public class MainActivity extends AppCompatActivity implements ActivityFragmentCallback {

    private int chooserType = 0;
    private Spanned result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill);
        substituteFragment();
    }

    private void substituteFragment() {
        int mFragEnterAnim = R.anim.slide_in_from_right;
        int mFragExitAnim = R.anim.slide_out_to_left;
        int mFragPopEnterAnim = R.anim.slide_in_from_left;
        int mFragPopExitAnim = R.anim.slide_out_to_right;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (chooserType == 0) {
            ft.add(R.id.frag_container, GetStartedFragment.getInstance()).commit();
        } else if (chooserType == 1) {
            ft.setCustomAnimations(mFragEnterAnim, mFragExitAnim, mFragPopEnterAnim, mFragPopExitAnim);
            ft.replace(R.id.frag_container, AddFragment.getInstance()).commit();
        } else if (chooserType == 2) {
            ft.setCustomAnimations(mFragEnterAnim, mFragExitAnim, mFragPopEnterAnim, mFragPopExitAnim);
            ft.replace(R.id.frag_container, FilledFragment.getInstance()).commit();
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }

    @Override
    public void onGetStarted() {
        chooserType = 1;
        substituteFragment();
    }

    @Override
    public void onAllFilled(Spanned spanResult) {
        UIUtils.hideSoftKeyboard(this);
        chooserType = 2;
        this.result = spanResult;
        substituteFragment();
    }

    public Spanned getResult() {
        return result;
    }
}
