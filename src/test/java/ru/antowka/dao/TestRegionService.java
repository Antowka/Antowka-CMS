package ru.antowka.dao;


import org.junit.Test;
import org.junit.Assert;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.Region;
import ru.antowka.service.RegionService;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class TestRegionService {

    @InjectMocks
    private RegionService testingObject;

    @Mock
    private RegionDao regionDao;

    @Spy
    private MessageResponse messageResponse;

    @Test
    public void testCreateRegion(){

        Region regionArgs = new Region();
        regionArgs.setTitle("test title");
        regionArgs.setDescription("Test description");

        Region regionReturn = new Region();
        regionReturn.setRegionId(1);
        regionReturn.setTitle("test title");
        regionReturn.setDescription("Test description");

        Mockito.when(regionDao.createRegion(regionArgs)).thenReturn(regionReturn);

        MessageResponse result = testingObject.createRegion(regionArgs);
        Assert.assertEquals(result.getCode(), 0);
    }

    @Test
    public void testGetAllRegions(){

        List<Region> regions = new ArrayList<Region>();

        Region region = new Region();
        region.setRegionId(125);
        region.setTitle("test title");
        region.setDescription("Test description");

        regions.add(region);

        Mockito.when(regionDao.getAllRegions()).thenReturn(regions);

        List<Region> result = testingObject.getAllRegions();

        Assert.assertEquals(result, regions);
    }

    @Test
    public void testGetRegionsByLevel(){

        List<Region> regions = new ArrayList<Region>();

        Region region = new Region();
        region.setRegionId(125);
        region.setTitle("test title");
        region.setDescription("Test description");

        regions.add(region);

        Mockito.when(regionDao.getAllRegionsByLevel(5)).thenReturn(regions);

        List<Region> result = testingObject.getRegionsByLevel(5);

        Assert.assertEquals(result, regions);
    }

    @Test
    public void testRemoveRegion(){
        Mockito.when(regionDao.removeRegion(5)).thenReturn(true);
        MessageResponse result = testingObject.removeRegion(5);
        Assert.assertTrue(result.getCode() == 0);
    }
}