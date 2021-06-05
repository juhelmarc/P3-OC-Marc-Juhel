package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavoriteEvent {
    public Neighbour neighbour;

    public DeleteFavoriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
