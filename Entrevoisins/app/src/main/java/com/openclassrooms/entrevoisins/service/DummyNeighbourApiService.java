package com.openclassrooms.entrevoisins.service;

import android.content.Context;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();




    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove( neighbour );
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add( neighbour );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavoriteNeighbours() {

        List<Neighbour> favoriteNeighbours = new ArrayList<>();
        for (Neighbour neighbour : neighbours) {
            if (neighbour.isFavorite()) {
                favoriteNeighbours.add(neighbour);
            }
        }
        return favoriteNeighbours;
    }

    @Override
    public void switchFavorite(Neighbour neighbour) {
        neighbour.setFavorite( !neighbour.isFavorite() );
        int index = neighbours.indexOf(neighbour);
        if(index >= 0 && index < neighbours.size()) {
                neighbours.get( index ).setFavorite( neighbour.isFavorite() );
        }


    }
}













