package org.workPall.services.interfaces;

import org.workPall.entities.Space;

import java.util.Map;

public interface SpaceServiceInter {
    Space addSpace(Space space);
    Map<Integer, Space> getAllSpaces();
    boolean deleteSpaceByName(String name);
}