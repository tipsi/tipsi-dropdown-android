package com.gettipsi.testmodule.action;

import android.app.Activity;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.view.WindowManager;

import com.gettipsi.tpsdropdown.Dropdown;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

public class UnlockKeyguardAction implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return isAssignableFrom(Dropdown.class);
    }

    @Override
    public String getDescription() {
        return "UnlockKeyguardAction";
    }

    @Override
    public void perform(UiController uiController, View view) {
        ((Activity)view.getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }
}
