package ru.antowka.dao;


import org.junit.Test;
import org.junit.Assert;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.antowka.entity.Region;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {
        "file:src/main/webapp/WEB-INF/properties/main.properties",
        "file:src/main/webapp/WEB-INF/properties/db.properties"
})
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
    private RegionDao regionDao;

    @Test
    public void testCreateRegion(){

        Region region = new Region();
        region.setTitle("test title");
        region.setDescription("Test description");

        int regionId = regionDao.createRegion(region).getRegionId();

        Assert.assertTrue(regionId > 0);
    }
}