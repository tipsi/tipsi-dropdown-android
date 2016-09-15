package com.gettipsi.testmodule.action;

import android.app.Activity;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.view.WindowManager;

import org.hamcrest.Matcher;

public class UnlockKeyguardAction implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void perform(UiController uiController, View view) {
        ((Activity)view.getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }
}
