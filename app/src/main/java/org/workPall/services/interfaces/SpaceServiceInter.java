package org.workPall.services.interfaces;

import org.workPall.entities.Space;

import java.util.Map;

public interface SpaceServiceInter {
    Space addSpace(Space space);
    boolean modifySpaceByName(Space space);
    Map<Integer, Space> getAllSpaces();
    Map<Integer, Space> getSpacesByName(String name);
    boolean deleteSpaceByName(int id);
    Map<Integer, Space> searchSpaces(String name, String location, Boolean isAvailable);

}