package com.android.gibbrfill;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Akanksha on 13/5/16
 */
public class UIUtils {

    public static void hideSoftKeyboard(Activity activity) {
        if (activity == null || activity.getCurrentFocus() == null) {
            return;
        }
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
