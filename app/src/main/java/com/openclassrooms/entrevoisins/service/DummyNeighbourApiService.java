package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favorites = new ArrayList<Neighbour>();


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
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavoritesNeighbours() {
        return favorites;
    }

    @Override
    public Boolean getFavoriteNeighbour(Neighbour neighbour) {
        if (favorites != null) {
            return favorites.contains(neighbour);
        } else {
            return false;
        }
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        favorites.remove(neighbour);
    }

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        favorites.add(neighbour);
    }
}
