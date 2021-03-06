package com.gettipsi.testmodule.action;

import android.app.Activity;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.view.WindowManager;

import com.gettipsi.tpsdropdown.Dropdown;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

public class SetSelectedAction implements ViewAction {

    private int selectionId;

    public SetSelectedAction(int selectionId) {
        this.selectionId = selectionId;
    }

    @Override
    public Matcher<View> getConstraints() {
        return isAssignableFrom(Dropdown.class);
    }

    @Override
    public String getDescription() {
        return "DropdownSelectionAction";
    }

    @Override
    public void perform(UiController uiController, View view) {
        ((Activity)view.getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        ((Dropdown) view).setSelected(selectionId);
    }
}
