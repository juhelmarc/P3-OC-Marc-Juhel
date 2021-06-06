package com.openclassrooms.entrevoisins.service;

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
        //Clean the list before get it //
        List<Neighbour> favoriteNeighbours = new ArrayList<>();
        //Pour chaque élement de la liste neighbours, nous allons vérrifier si favorite == true avec la méthode isFavorite() si il l'est il sera ajouté à la liste favoriteNeighbour avec la méthode .add
        for (Neighbour neighbour : neighbours) {
            if (neighbour.isFavorite()) {
                favoriteNeighbours.add(neighbour);
            }
        }
        //la va leur retournée par la méthode est la liste des favoris qu'on pourra utiliser ensuite dans notre fragment pour afficher la liste des favoris
        return favoriteNeighbours;
    }
    //verifier que l'index n'est pas égal à -1 (mettre une sécurité)
    @Override
    public void switchNeighbourTypeFavorite(Neighbour neighbour, boolean isFavorite) {

        //isFavorite = neighbour.isFavorite();
        if (!isFavorite) {
            neighbours.get(index).setFavorite( true );
        }
        else {
            neighbours.get(index).setFavorite( false );
        }
        // Si je remplace neighbours.get(index).setFavorite(true) par neighbour.setFavorite(true) ça ne marche pas
        // réponse ? : modification d'un objet d'une liste, mais pas de la liste contenant l'objet
        // alors qu'avec neighbours.get... nous modifions directement la liste
        //utiliser l'id pour réaliser la comparaison et les modifications

    }
}
