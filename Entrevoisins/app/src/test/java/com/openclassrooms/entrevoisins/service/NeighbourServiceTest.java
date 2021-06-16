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

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        service.switchFavorite(service.getNeighbours().get(0), false );
        service.switchFavorite(service.getNeighbours().get(1), false );

    }

   /*** @Before
    public void setFavorite() {

    }
*/
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
        //utiliser la méthode switchfavorite
        //Neighbour neighbour0 = service.getNeighbours().get(0);
        //Neighbour neighbour1 = service.getNeighbours().get(1);

        List<Neighbour> favoriteNeighbours = service.getFavoriteNeighbours();
        List<Neighbour> expectedFavoriteNeighbours  = Arrays.asList(  service.getNeighbours().get(0), service.getNeighbours().get(1));
        assertThat(favoriteNeighbours , IsIterableContainingInAnyOrder.containsInAnyOrder(expectedFavoriteNeighbours.toArray()));
        assertEquals( favoriteNeighbours.size(), 2 );
    }

    // Test switch méthode
    @Test
    public void switchUnFavoriteWithSuccess() {
        //service.getNeighbours().get(0).setFavorite( true );
        //service.getNeighbours().get(1).setFavorite( true );

        assertTrue( service.getNeighbours().get(0).isFavorite() );
        assertTrue( service.getNeighbours().get(1).isFavorite() );
        Neighbour neighbourToSwitchUnFavorite = service.getNeighbours().get(0);
        service.switchFavorite( neighbourToSwitchUnFavorite , true );
        assertFalse(neighbourToSwitchUnFavorite.isFavorite());
        //on valorise isFavoriteAfterSwitch avec la nouvelle valeur de neighbourToSwitch.isFvorite()
        //isFavoriteAfterSwitch = neighbourToSwitch.isFavorite();
        // after switch isFavorite should be equal to isFavoriteAfterSwitch donc (isFavorite == isFavoriteAfterSwitch) la méthode switchFavorite fonctionne donc bien
        // car la valeur du boolean neighbourToSwitch.isFavorite passe bien de vrai à faux à chaque fois qu'on fait appel à la méthode sur l'objet neighbourToSwitch
        //- 2.1
        //assertTrue( isFavorite == isFavoriteAfterSwitch );
        assertEquals( service.getFavoriteNeighbours().size(), 1);
        /**- 2.2
         Neighbour neighbourToTest2 = service.getNeighbours().get(0);
         Boolean isFavoriteTest2 = neighbourToTest2.isFavorite();
         assertTrue( isFavoriteTest2 == isFavoriteAfterSwitch );
         */
        //todo : tester la méthode createneighbour
    }
    @Test
    public void switchFavoriteWithSuccess() {

        Neighbour neighbourToSwitchFavorite = service.getNeighbours().get( 2 );
        assertFalse( neighbourToSwitchFavorite.isFavorite() );
        assertTrue( service.getNeighbours().get(1).isFavorite() );
        //- 1
        service.switchFavorite( neighbourToSwitchFavorite, false );

        //test if result = false donc (isFavorite != isFavoriteAfterSwitch)
        //- 1.1
        assertTrue( neighbourToSwitchFavorite.isFavorite() );
        assertEquals( service.getFavoriteNeighbours().size(), 3 );
    }
        /**- 1.2
        Neighbour neighbourToTest = service.getNeighbours().get(0);
        Boolean isFavoriteTest = neighbourToTest.isFavorite();
        assertTrue( isFavoriteTest == isFavoriteAfterSwitch );
        */
        //- 2
        // créer un nouveau test pour le switch to unfavorite

}
