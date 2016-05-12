package com.android.gibbrfill;

import android.text.Spanned;

/**
 * Created by Akanksha on 12/5/16
 */
public interface ActivityFragmentCallback {

    void onGetStarted();

    void onAllFilled(Spanned spanned);
}
