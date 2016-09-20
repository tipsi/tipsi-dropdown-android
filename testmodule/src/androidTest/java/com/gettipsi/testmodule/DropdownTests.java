package com.gettipsi.testmodule;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.endsWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DropdownTests {

    private List<Object> items;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void initValidData() {
        items = Arrays.<Object>asList("One", "Two", "Three", "Four");
    }

    @Test
    public void checkForCorrectViews() {
        onView(withId(R.id.dropdown))
                .check(matches(withClassName(endsWith("DropdownContainer"))));
        onView(withId(R.id.dropdownId))
                .check(matches(withClassName(endsWith("Dropdown"))));
    }

    @Test
    public void checkItemsSelection() {
        setupItems();

        onView(withId(R.id.dropdownId))
                .perform(new SetSelectedAction(0))
                .check(matches(withSpinnerText(items.get(0).toString())));

        onView(withId(R.id.dropdownId))
                .perform(new SetSelectedAction(1))
                .check(matches(withSpinnerText(items.get(1).toString())));

        onView(withId(R.id.dropdownId))
                .perform(new SetSelectedAction(2))
                .check(matches(withSpinnerText(items.get(2).toString())));

        onView(withId(R.id.dropdownId))
                .perform(new SetSelectedAction(3))
                .check(matches(withSpinnerText(items.get(3).toString())));
    }

    @Test
    public void checkItemsWithNameSelection() {
        setupItems();
        onView(withId(R.id.dropdownId))
                .perform(new SelectElementWithNameAction(items.get(0).toString()))
                .check(matches(withSpinnerText(items.get(0).toString())));

        onView(withId(R.id.dropdownId))
                .perform(new SelectElementWithNameAction(items.get(1).toString()))
                .check(matches(withSpinnerText(items.get(1).toString())));

        onView(withId(R.id.dropdownId))
                .perform(new SelectElementWithNameAction(items.get(2).toString()))
                .check(matches(withSpinnerText(items.get(2).toString())));

        onView(withId(R.id.dropdownId))
                .perform(new SelectElementWithNameAction(items.get(3).toString()))
                .check(matches(withSpinnerText(items.get(3).toString())));
    }

    private void setupItems() {
        onView(withId(R.id.dropdownId))
                .perform(new SetupElementsAction(items))
                .check(ViewAssertions.matches(DropdownMatcher.withListSize(items.size())));
    }

}
