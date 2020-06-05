package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     *  Generate the list of the favorites neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavoritesNeighbours();

    /**
     * Find a neighbour from the favorites list
     * @param neighbour
     * @return {@link Boolean}
     */
    Boolean getFavoriteNeighbour(Neighbour neighbour);

    /**
     * Deletes a neighbour from the favorites
     * @param neighbour
     */
    void deleteFavoriteNeighbour(Neighbour neighbour);

    /**
     * Add a neighbour to the list of the favorites
     * @param neighbour
     */
    void addFavoriteNeighbour(Neighbour neighbour);
}
