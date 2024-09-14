package org.workPall.services.impl;

import org.workPall.entities.Space;
import org.workPall.repositories.impl.SpaceRepositoryImpl;
import org.workPall.services.interfaces.SpaceServiceInter;

import java.util.Map;
import java.util.Optional;

public class SpaceServiceImpl implements SpaceServiceInter {
    private final SpaceRepositoryImpl spaceRepository;

    public SpaceServiceImpl(SpaceRepositoryImpl spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    public Space addSpace(Space space) {
        spaceRepository.createSpace(space);
        return space;
    }

    public boolean deleteSpaceByName(String name) {
        Optional<Integer> spaceIdOpt = spaceRepository.findSpaceIdByName(name);

        if (spaceIdOpt.isPresent()) {
            int spaceId = spaceIdOpt.get();
            return spaceRepository.deleteSpaceById(spaceId);
        } else {
            System.out.println("Space with name '" + name + "' not found.");
            return false;
        }
    }

    @Override
    public Map<Integer, Space> getAllSpaces() {
        return spaceRepository.displayAllSpaces();
    }

}