package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;
    private List<Neighbour> listNeighbours;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        listNeighbours = service.getNeighbours();
        listNeighbours.get(0).setFavorite( true );
        listNeighbours.get(1).setFavorite( true );


    }

   /*** @Before
    public void setFavorite() {

    }
*/
    @Test
    public void getNeighboursWithSuccess() {
        //List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(listNeighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = listNeighbours.get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(listNeighbours.contains(neighbourToDelete));
    }
    //Test favoriteNeighbour
    @Test
    public void getFavoritesWithSuccess() {

        List<Neighbour> favoriteNeighbours = service.getFavoriteNeighbours();
        List<Neighbour> expectedFavoriteNeighbours  = Arrays.asList(  listNeighbours.get(0), listNeighbours.get(1));
        assertThat(favoriteNeighbours , IsIterableContainingInAnyOrder.containsInAnyOrder(expectedFavoriteNeighbours.toArray()));
        assertEquals( favoriteNeighbours.size(), 2 );
    }
    @Test
    public void createNeighbourWithSuccess() {
        //List<Neighbour> neighbourList = service.getNeighbours();
        Neighbour neighbourToCreate = new Neighbour( System.currentTimeMillis(), "test","https://i.pravatar.cc/150?u="+ System.currentTimeMillis(),
                "test", "","", false );
        service.createNeighbour( neighbourToCreate );
        assertEquals( listNeighbours.size(), 13 );
        assertTrue(listNeighbours.contains( neighbourToCreate ));
    }

    @Test
    public void switchUnFavoriteWithSuccess() {
        assertTrue( listNeighbours.get(0).isFavorite() );
        assertTrue( listNeighbours.get(1).isFavorite() );
        Neighbour neighbourToSwitchUnFavorite = listNeighbours.get(0);
        service.switchFavorite( neighbourToSwitchUnFavorite );
        assertFalse(neighbourToSwitchUnFavorite.isFavorite());
        assertEquals( service.getFavoriteNeighbours().size(), 1);


    }
    @Test
    public void switchFavoriteWithSuccess() {
        Neighbour neighbourToSwitchFavorite = listNeighbours.get( 2 );
        assertFalse( neighbourToSwitchFavorite.isFavorite() );
        assertTrue( listNeighbours.get(1).isFavorite() );
        assertTrue( listNeighbours.get(0).isFavorite() );
        service.switchFavorite( neighbourToSwitchFavorite);
        assertTrue( neighbourToSwitchFavorite.isFavorite() );
        assertEquals( service.getFavoriteNeighbours().size(), 3 );
    }


}
