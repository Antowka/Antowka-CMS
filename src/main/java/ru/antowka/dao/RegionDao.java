package ru.antowka.dao;

import ru.antowka.entity.Region;

import java.util.List;

/**
 * Created by Anton Nik on 28.08.15.
 */
public interface RegionDao {

    /**
     * Create new region
     *
     * @param region
     * @return
     */
    Region createRegion(Region region);

    /**
     * Get all regions in tree
     *
     * @return
     */
    List<Region> getAllRegions();

    /**
     * Get all regions from level and down in free
     *
     * @param level
     * @return
     */
    List<Region> getAllRegionsByLevel(int level);

    /**
     * Edit region
     *
     * @param region
     * @return
     */
    Region editRegion(Region region);

    /**
     * Remove region and all child-regions after
     *
     * @param regionId
     * @return
     */
    boolean removeRegion(int regionId);

}
