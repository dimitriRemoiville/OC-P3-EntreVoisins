
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteFavoriteViewAction;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;
    private List<Neighbour> mNeighbourList = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
    private NeighbourApiService mApiService;
    private List<Neighbour> mFavoritesNeighbours;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mApiService = DI.getNeighbourApiService();
        mFavoritesNeighbours = mApiService.getFavoritesNeighbours();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void myNeighboursList_displayActivityDetails() {
        //Given : Click on the first item of the neighbours list
        onView(ViewMatchers.withId(R.id.list_neighbours)).
                perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Then : Check that the activity details is displayed
        onView(ViewMatchers.withId(R.id.activity_details)).check(matches(isDisplayed()));
    }

    @Test
    public void myNeighboursList_checkNameOnActivityDetails() {
        //Given : the first neighbour of the list
        Neighbour neighbour = mNeighbourList.get(0);

        //When : Click on it
        onView(ViewMatchers.withId(R.id.list_neighbours)).
                perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //Then : the name on the activity details is the good one
        onView(ViewMatchers.withId(R.id.activity_details_name1_text))
                .check(matches(withText(neighbour.getName())));
    }

    @Test
    public void myFavoritesList_checkFavoritesList() {
        //Given : the favorite List is empty
        onView(ViewMatchers.withId(R.id.container)).perform(scrollRight());
        mFavoritesNeighbours.clear();
        onView(ViewMatchers.withId(R.id.list_favorites)).check(withItemCount(0));

        //When : add 2 neighbours in the favorite List
        mApiService.addFavoriteNeighbour(mNeighbourList.get(0));
        mApiService.addFavoriteNeighbour(mNeighbourList.get(1));

        //Then : the favorite list is containing 2 neighbours
        onView(ViewMatchers.withId(R.id.list_favorites)).check(withItemCount(2));
    }

    @Test
    public void myFavoritesList_deleteAction_shouldRemoveItem() {
        //Given : the favorite list is empty and we add 3 neighbours
        onView(ViewMatchers.withId(R.id.container)).perform(scrollRight());
        mFavoritesNeighbours.clear();
        onView(ViewMatchers.withId(R.id.list_favorites)).check(withItemCount(0));
        for (int i = 0;i < 3; i++) {
            mApiService.addFavoriteNeighbour(mNeighbourList.get(i));
        }
        onView(ViewMatchers.withId(R.id.list_favorites)).check(withItemCount(3));

        //When : perform a click on the delete button of the third item of the favorite list
        onView(ViewMatchers.withId(R.id.list_favorites))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new DeleteFavoriteViewAction()));

        // Then : the number of element is now 2
        onView(ViewMatchers.withId(R.id.list_favorites)).check(withItemCount(2));
    }
}