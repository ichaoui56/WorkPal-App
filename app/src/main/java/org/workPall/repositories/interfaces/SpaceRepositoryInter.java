package org.workPall.repositories.interfaces;

import org.workPall.entities.Space;

import java.util.Map;
import java.util.Optional;

public interface SpaceRepositoryInter {
    Space createSpace(Space space);
    // Method to find space ID by name
    Optional<Integer> findSpaceIdByName(String name);

    // Method to delete space by ID
    boolean deleteSpaceById(int id);
    Map<Integer, Space> displayAllSpaces();

}