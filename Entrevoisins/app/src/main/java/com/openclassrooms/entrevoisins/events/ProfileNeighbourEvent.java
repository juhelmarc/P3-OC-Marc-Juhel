package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class ProfileNeighbourEvent {

    public Neighbour neighbour;

    public ProfileNeighbourEvent (Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
