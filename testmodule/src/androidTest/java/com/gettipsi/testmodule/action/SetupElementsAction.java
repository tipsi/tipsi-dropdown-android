package com.gettipsi.testmodule.action;

import android.app.Activity;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.view.WindowManager;

import com.gettipsi.tpsdropdown.Dropdown;

import org.hamcrest.Matcher;

import java.util.List;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

public class SetupElementsAction implements ViewAction {

    private List<Object> items;

    public SetupElementsAction(List<Object> items) {
        this.items = items;
    }

    @Override
    public Matcher<View> getConstraints() {
        return isAssignableFrom(Dropdown.class);
    }

    @Override
    public String getDescription() {
        return "SetupElementsAction";
    }

    @Override
    public void perform(UiController uiController, View view) {
        ((Activity)view.getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        ((Dropdown) view).setupWithElements(items);
    }
}
