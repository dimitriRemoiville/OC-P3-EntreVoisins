package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavoriteEvent {
    /**
     * Neighbour to delete
     */
    public final Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteFavoriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
