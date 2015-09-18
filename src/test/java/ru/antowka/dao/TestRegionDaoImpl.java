package ru.antowka.dao;


import org.junit.Test;
import org.junit.Assert;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.Region;
import ru.antowka.service.RegionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/config/main-config.xml",
        "file:src/main/webapp/WEB-INF/config/database-config.xml",
        "file:src/main/webapp/WEB-INF/config/controllers-config.xml",
        "file:src/main/webapp/WEB-INF/config/entity-config.xml",
        "file:src/main/webapp/WEB-INF/config/services-config.xml",
        "file:src/main/webapp/WEB-INF/config/lang-config.xml"
})
public class TestRegionDaoImpl {

    @Autowired
    private RegionService regionService;

    @Test
    public void testCreateRegion(){

        Region region = new Region();
        region.setTitle("test title");
        region.setDescription("Test description");
        MessageResponse resalt = regionService.createRegion(region);
        Assert.assertTrue(resalt.getCode() == 0);
    }
}