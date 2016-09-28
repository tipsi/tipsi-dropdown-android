package com.gettipsi.testmodule.action;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.gettipsi.tpsdropdown.DropdownContainer;

import org.hamcrest.Matcher;

public class SetStyleAction implements ViewAction {

    private String style;

    public SetStyleAction(String style) {
        this.style = style;
    }

    @Override
    public Matcher<View> getConstraints() {
        return ViewMatchers.isAssignableFrom(DropdownContainer.class);
    }

    @Override
    public String getDescription() {
        return "SetStyleAction";
    }

    @Override
    public void perform(UiController uiController, View view) {
        ((DropdownContainer) view).setStyle(style);
    }
}
