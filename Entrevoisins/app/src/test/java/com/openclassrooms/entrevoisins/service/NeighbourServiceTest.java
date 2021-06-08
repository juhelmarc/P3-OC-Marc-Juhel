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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Before
    public void setFavorite() {
        service.getNeighbours().get(0).setFavorite( true );
        service.getNeighbours().get(1).setFavorite( true );
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }
    //Test favoriteNeighbour
    @Test
    public void getFavoritesWithSuccess() {
        List<Neighbour> favoriteNeighbours = service.getFavoriteNeighbours();
        List<Neighbour> expectedFavoriteNeighbours  = Arrays.asList(  service.getNeighbours().get(0), service.getNeighbours().get(1));
        assertThat(favoriteNeighbours , IsIterableContainingInAnyOrder.containsInAnyOrder(expectedFavoriteNeighbours .toArray()));
    }

    // Test switch méthode
    @Test
    public void switchFavoriteWithSuccess() {
        Neighbour neighbourToSwitch = service.getNeighbours().get(0);
        //on stock le résultat dans isFavorite
        Boolean isFavorite = neighbourToSwitch.isFavorite();
        //- 1
        service.switchFavorite( neighbourToSwitch, isFavorite);
        Boolean isFavoriteAfterSwitch = neighbourToSwitch.isFavorite();
        //test if result = false donc (isFavorite != isFavoriteAfterSwitch)
        //- 1.1
        assertFalse(isFavorite == isFavoriteAfterSwitch);
        //- 1.2
        Neighbour neighbourToTest = service.getNeighbours().get(0);
        Boolean isFavoriteTest = neighbourToTest.isFavorite();
        assertFalse( isFavoriteTest != isFavoriteAfterSwitch );

        //- 2
        service.switchFavorite( neighbourToSwitch , isFavoriteAfterSwitch );
        //on valorise isFavoriteAfterSwitch avec la nouvelle valeur de neighbourToSwitch.isFvorite()
        isFavoriteAfterSwitch = neighbourToSwitch.isFavorite();
        // after switch isFavorite should be equal to isFavoriteAfterSwitch donc (isFavorite == isFavoriteAfterSwitch) la méthode switchFavorite fonctionne donc bien
        // car la valeur du boolean neighbourToSwitch.isFavorite passe bien de vrai à faux à chaque fois qu'on fait appel à la méthode sur l'objet neighbourToSwitch
        //- 2.1
        assertFalse( isFavorite != isFavoriteAfterSwitch );
        //- 2.2
        Neighbour neighbourToTest2 = service.getNeighbours().get(0);
        Boolean isFavoriteTest2 = neighbourToTest2.isFavorite();
        assertFalse( isFavoriteTest2 != isFavoriteAfterSwitch );
    }
}
