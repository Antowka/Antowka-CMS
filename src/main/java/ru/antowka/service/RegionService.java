package ru.antowka.service;

import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.Region;

import java.util.List;

/**
 * Created by anton on 17.03.16.
 */
public interface RegionService {

    MessageResponse createRegion(Region region);

    List<Region> getAllRegions();

    List<Region> getRegionsByLevel(int level);

    MessageResponse removeRegion(int regionId);
}
