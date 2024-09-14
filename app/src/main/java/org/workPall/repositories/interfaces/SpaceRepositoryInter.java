package org.workPall.repositories.interfaces;

import org.workPall.entities.Space;

import java.util.Map;
import java.util.Optional;

public interface SpaceRepositoryInter {
    Space createSpace(Space space);
    Boolean modifySpace(Space space);
    Map<Integer, Space> getSpacesByName(String name);
    Optional<Integer> findSpaceIdByName(String name);
    boolean deleteSpaceById(int id);
    Map<Integer, Space> displayAllSpaces();
    Map<Integer, Space> searchSpaces(String name, String location, Boolean isAvailable);


}