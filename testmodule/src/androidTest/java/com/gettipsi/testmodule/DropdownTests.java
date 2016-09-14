package com.gettipsi.testmodule;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.WindowManager;

import com.gettipsi.testmodule.action.SelectElementWithNameAction;
import com.gettipsi.testmodule.action.SetSelectedAction;
import com.gettipsi.testmodule.action.SetupElementsAction;
import com.gettipsi.testmodule.matcher.DropdownMatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DropdownTests {

    private List<Object> items;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void initValidData() {
        final MainActivity activity = activityRule.getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });
        items = Arrays.<Object>asList("One", "Two", "Three", "Four");
    }

    @Test
    public void checkForCorrectViews() {
        onView(withId(R.id.dropdown))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withClassName(endsWith("DropdownContainer"))));
        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withClassName(endsWith("Dropdown"))));
    }

    @Test
    public void checkItemsSelection() {
        setupItems();

        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(new SetSelectedAction(0))
                .check(matches(withSpinnerText(items.get(0).toString())));

        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(new SetSelectedAction(1))
                .check(matches(withSpinnerText(items.get(1).toString())));

        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(new SetSelectedAction(2))
                .check(matches(withSpinnerText(items.get(2).toString())));

        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(new SetSelectedAction(3))
                .check(matches(withSpinnerText(items.get(3).toString())));
    }

    @Test
    public void checkItemsWithNameSelection() {
        setupItems();
        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(new SelectElementWithNameAction(items.get(0).toString()))
                .check(matches(withSpinnerText(items.get(0).toString())));

        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(new SelectElementWithNameAction(items.get(1).toString()))
                .check(matches(withSpinnerText(items.get(1).toString())));

        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(new SelectElementWithNameAction(items.get(2).toString()))
                .check(matches(withSpinnerText(items.get(2).toString())));

        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(new SelectElementWithNameAction(items.get(3).toString()))
                .check(matches(withSpinnerText(items.get(3).toString())));
    }

    @Test
    public void checkItemsClickSelection() {
        setupItems();
        String item = items.get(0).toString();
        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(item)))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withSpinnerText(containsString(item))));

        String item1 = items.get(1).toString();
        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(item1)))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withSpinnerText(containsString(item1))));

        String item2 = items.get(2).toString();
        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(item2)))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withSpinnerText(containsString(item2))));

        String item3 = items.get(3).toString();
        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(item3)))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withSpinnerText(containsString(item3))));
    }

    private void setupItems() {
        onView(withId(R.id.dropdownId))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(new SetupElementsAction(items))
                .check(ViewAssertions.matches(DropdownMatcher.withListSize(items.size())));
    }

}
