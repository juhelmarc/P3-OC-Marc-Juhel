package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListNeighbourActivityTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>( ListNeighbourActivity.class );

    @Test
    public void listNeighbourActivityTest() {
        ViewInteraction floatingActionButton = onView(
                allOf( withId( R.id.add_neighbour ),
                        childAtPosition(
                                allOf( withId( R.id.main_content ),
                                        childAtPosition(
                                                withId( android.R.id.content ),
                                                0 ) ),
                                2 ),
                        isDisplayed() ) );
        floatingActionButton.perform( click() );

        ViewInteraction textInputEditText = onView(
                allOf( withId( R.id.name ),
                        childAtPosition(
                                childAtPosition(
                                        withId( R.id.nameLyt ),
                                        0 ),
                                0 ),
                        isDisplayed() ) );
        textInputEditText.perform( click() );


        textInputEditText.perform( replaceText( "test" ), closeSoftKeyboard() );



        onView(  withId( R.id.create )).perform( click() );

        ViewInteraction recyclerView = onView(
                allOf( withId( R.id.list_neighbours ),
                        withParent( withId( R.id.container ) ) ) );
        recyclerView.perform( actionOnItemAtPosition( 12, click() ) );

        ViewInteraction floatingActionButton2 = onView(
                allOf( withId( R.id.floatingFavoriteButton ), withContentDescription( "add button" ),
                        childAtPosition(
                                allOf( withId( R.id.constraintLayout2 ),
                                        childAtPosition(
                                                withId( R.id.details_activity ),
                                                0 ) ),
                                5 ) ) );
        floatingActionButton2.perform( scrollTo(), click() );

        ViewInteraction appCompatImageView = onView(
                allOf( withId( R.id.Up ), withContentDescription( "back button" ),
                        childAtPosition(
                                allOf( withId( R.id.constraintLayout2 ),
                                        childAtPosition(
                                                withId( R.id.details_activity ),
                                                0 ) ),
                                1 ) ) );
        appCompatImageView.perform( scrollTo(), click() );

        ViewInteraction tabView = onView(
                allOf( withContentDescription( "Favorites" ),
                        childAtPosition(
                                childAtPosition(
                                        withId( R.id.tabs ),
                                        0 ),
                                1 ),
                        isDisplayed() ) );
        tabView.perform( click() );

        ViewInteraction viewPager = onView(
                allOf( withId( R.id.container ),
                        childAtPosition(
                                allOf( withId( R.id.main_content ),
                                        childAtPosition(
                                                withId( android.R.id.content ),
                                                0 ) ),
                                1 ),
                        isDisplayed() ) );
        viewPager.perform( swipeLeft() );

        ViewInteraction appCompatImageButton = onView(
                allOf( withId( R.id.item_list_delete_button ),
                        childAtPosition(
                                allOf( withId( R.id.item_list ),
                                        childAtPosition(
                                                withId( R.id.favorite_neighbours ),
                                                0 ) ),
                                2 ),
                        isDisplayed() ) );
        appCompatImageButton.perform( click() );

        ViewInteraction materialButton2 = onView(
                allOf( withId( R.id.snackbar_action ), withText( "YES" ),
                        childAtPosition(
                                childAtPosition(
                                        withClassName( is( "android.support.design.widget.Snackbar$SnackbarLayout" ) ),
                                        0 ),
                                1 ),
                        isDisplayed() ) );
        materialButton2.perform( click() );

        ViewInteraction tabView2 = onView(
                allOf( withContentDescription( "My neighbours" ),
                        childAtPosition(
                                childAtPosition(
                                        withId( R.id.tabs ),
                                        0 ),
                                0 ),
                        isDisplayed() ) );
        tabView2.perform( click() );

        ViewInteraction viewPager2 = onView(
                allOf( withId( R.id.container ),
                        childAtPosition(
                                allOf( withId( R.id.main_content ),
                                        childAtPosition(
                                                withId( android.R.id.content ),
                                                0 ) ),
                                1 ),
                        isDisplayed() ) );
        viewPager2.perform( swipeRight() );
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText( "Child at position " + position + " in parent " );
                parentMatcher.describeTo( description );
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches( parent )
                        && view.equals( ((ViewGroup) parent).getChildAt( position ) );
            }
        };
    }
}
