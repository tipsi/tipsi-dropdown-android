package com.gettipsi.testmodule;

import android.os.SystemClock;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.gettipsi.testmodule.action.SelectElementWithNameAction;
import com.gettipsi.testmodule.action.SetSelectedAction;
import com.gettipsi.testmodule.action.SetStyleAction;
import com.gettipsi.testmodule.action.SetupElementsAction;
import com.gettipsi.testmodule.matcher.DropdownMatcher;
import com.gettipsi.tpsdropdown.DropdownStylist;
import com.gettipsi.tpsdropdown.model.Style;

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
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DropdownTests {

    private List<Object> items;
    private Style style;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initValidData() {
        items = Arrays.<Object>asList("One", "Two", "Three", "Four");
        style = new Style.Builder()
                .withBackgroundColor("0x0000FF")
                .withBorderWidth(2)
                .withBorderColor("0xFFFFFF")
                .withCornerRadius(10)
                .withSeparatorHeight(1)
                .withSeparatorColor("0xAAAAAA")
                .withFontSize(15)
                .withTextColor("0xCCCCCC")
                .withTextAlignment("Left")
                .withIndicatorImageName("custom_triangle.png")
                .build();
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

    @Test
    public void checkItemsClickSelection() {
        setupItems();

        String item = items.get(0).toString();
        onView(withId(R.id.dropdownId)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(item))).perform(click());
        onView(withId(R.id.dropdownId)).check(matches(withSpinnerText(containsString(item))));

        String item1 = items.get(1).toString();
        onView(withId(R.id.dropdownId)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(item1))).perform(click());
        onView(withId(R.id.dropdownId)).check(matches(withSpinnerText(containsString(item1))));

        String item2 = items.get(2).toString();
        onView(withId(R.id.dropdownId)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(item2))).perform(click());
        onView(withId(R.id.dropdownId)).check(matches(withSpinnerText(containsString(item2))));

        String item3 = items.get(3).toString();
        onView(withId(R.id.dropdownId)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(item3))).perform(click());
        onView(withId(R.id.dropdownId)).check(matches(withSpinnerText(containsString(item3))));
    }

    @Test
    public void checkSetupStyleBeforeAdapter() {
        onView(withId(R.id.dropdown))
                .perform(new SetStyleAction(style));

        onView(withId(R.id.dropdown))
                .perform(new SetupElementsAction(items))
                .check(ViewAssertions.matches(DropdownMatcher.withListSize(items.size())));
    }

    @Test
    public void checkForSetStyleViaSetters() {
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));
    }

    @Test
    public void checkIconChange() {
        setupItems();
        style.setIndicatorImageName("");
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        style.setIndicatorImageName(null);
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        style.setIndicatorImageName("custom_triangle");
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        style.setIndicatorImageName("custom_triangle.png");
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        style.setIndicatorImageResId(0);
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        style.setIndicatorImageResId(R.drawable.custom_triangle);
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));
    }

    @Test
    public void checkChangingBackground() {
        setupItems();
        DropdownStylist.getInstance().getStyle().setBackgroundColor("0x00FF00");
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        DropdownStylist.getInstance().getStyle().setBackgroundColor("0xFF0000");
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        DropdownStylist.getInstance().getStyle().setBackgroundColor("0xFF00FF");
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        DropdownStylist.getInstance().getStyle().setBackgroundColor("0x00FFFF");
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        DropdownStylist.getInstance().getStyle().setBackgroundColor("0xFFFFFF");
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        DropdownStylist.getInstance().getStyle().setBackgroundColor("0x000000");
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        DropdownStylist.getInstance().getStyle().setBackgroundColor("0xCCCCCC");
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));

        DropdownStylist.getInstance().getStyle().setBackgroundColor("0x666666");
        onView(withId(R.id.dropdown)).perform(new SetStyleAction(style));
    }

    private void setupItems() {
        onView(withId(R.id.dropdown))
                .perform(new SetupElementsAction(items))
                .check(ViewAssertions.matches(DropdownMatcher.withListSize(items.size())));

        onView(withId(R.id.dropdown))
                .perform(new SetStyleAction(style));
    }

}
