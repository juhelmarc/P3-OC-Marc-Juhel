
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.ProfileViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.transform.stream.StreamSource;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.actionWithAssertions;
import static android.support.test.espresso.action.ViewActions.addGlobalAssertion;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.bumptech.glide.GenericTransitionOptions.with;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView( withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView( withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT + 1));
        // When perform a click on a delete icon
        onView( withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView( withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
    }
    /**
     * When we click on item we should see
     */
    @Test
    public void myNeighboursList_itemClick_shouldStartDetailsNeighbourActivity() {

        // When perform a click on item
        onView( withId( R.id.list_neighbours ) )
                .perform( RecyclerViewActions.actionOnItemAtPosition( 2, new ProfileViewAction() ) );
        //onView(ViewMatchers.withId(R.id.profile_name)).check( (ViewAssertion) withText( "Chloé" ) );
        //perform click on Favorite button
        onView( withId( R.id.floatingFavoriteButton ) ).perform( click() );
        //tester le changement d'image du floatingbutton

        //perform click on back button
        onView( withId( R.id.Up ) ).perform( click() );
        //tester le changement d'image du deletebutton


        //onView(ViewMatchers.withId(R.id.tabItem2)).perform(click());
        // the number of element in favorite is 1 // toutes les vues du viewpager sont chargées
        onView(withContentDescription("Favorites")).perform( click() );
        onView( withId( R.id.favorite_neighbours ) ).check( withItemCount( 1 ) );
        onView( withContentDescription( "My neighbours" ) ).perform( click() );

        //perform click on deletebutton to neighbour in position 3

        onView( withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new DeleteViewAction()));

        // and click on snackbar button to validate the remove
        onView((withId(android.support.design.R.id.snackbar_action)))
                .perform(click());
        onView( withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
        //onView(ViewMatchers.withId(R.id.favorite_neighbours)).check(withItemCount(0));
        onView(withContentDescription("Favorites")).perform( click() );
        onView( withId(R.id.favorite_neighbours)).check(withItemCount(0));
    }
    @Test
    public void createNeighbour() {
        onView(withId(R.id.add_neighbour)).perform(click());
        onView( withId(R.id.name) ).perform(click(), replaceText(   "TEST" ), ViewActions.closeSoftKeyboard() );
        onView(withId(R.id.create)).perform(click());
        scrollTo();
        onView( withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT + 1));

    }
    //Vérifier que les informations dans detail_neighbour match bien
    //Méthode pour clicker sur le bouton favori, vérifier que ce favori est présent dans le tab favorite, réaliser la même action pour vérrifier que le voisin à bien été retirer des favoris
    //Il faudra aussi tester la supression d'un voisin lorsque celui-ci est dans les favoris

    //effectuer un test pour la supression d'un neighbour qui est Favorite

    //puis un test pour le creatneighbour
}