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

    @Override
    public Space addSpace(Space space) {
        spaceRepository.createSpace(space);
        return space;
    }

    @Override
    public boolean modifySpaceByName(Space space) {
        return spaceRepository.modifySpace(space);
    }

    @Override
    public Map<Integer, Space> getSpacesByName(String name) {
        return spaceRepository.getSpacesByName(name);
    }
    @Override
    public boolean deleteSpaceByName(int id) {
        return spaceRepository.deleteSpaceById(id);
    }


    @Override
    public Map<Integer, Space> getAllSpaces() {
        return spaceRepository.displayAllSpaces();
    }

}