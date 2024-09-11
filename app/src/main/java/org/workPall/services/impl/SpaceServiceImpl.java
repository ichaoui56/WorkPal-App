package org.workPall.services.impl;

import org.workPall.entities.Space;
import org.workPall.repositories.impl.SpaceRepositoryImpl;
import org.workPall.services.interfaces.SpaceServiceInter;

import java.util.Map;

public class SpaceServiceImpl implements SpaceServiceInter {
    private final SpaceRepositoryImpl spaceRepository;

    public SpaceServiceImpl(SpaceRepositoryImpl spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    public Space addSpace(Space space) {
        spaceRepository.createSpace(space);
        return space;
    }

    @Override
    public Map<Integer, Space> getAllSpaces() {
        return spaceRepository.displayAllSpaces();
    }
}
