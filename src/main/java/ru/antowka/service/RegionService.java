package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.RegionDao;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.Region;

import java.util.List;

/**
 * Created by Anton Nik on 29.08.15.
 */
@Service
public class RegionService {

    @Autowired
    private RegionDao regionDao;

    @Autowired
    private MessageResponse messageResponse;

    public MessageResponse createRegion(Region region){

        if(regionDao.createRegion(region).getRegionId() > 0) {

            messageResponse.setCode(0);
            messageResponse.setType("region");
            messageResponse.setTitle("Region has been created");
        }else{

            messageResponse.setCode(1);
            messageResponse.setType("region");
            messageResponse.setTitle("Region has not created");
        }

        return messageResponse;
    }

    public List<Region> getAllRegions(){
        return regionDao.getAllRegions();
    }

    public List<Region> getRegionsByLevel(int level){
        return regionDao.getAllRegionsByLevel(level);
    }

    public MessageResponse removeRegion(int regionId){

        if(regionDao.removeRegion(regionId)) {

            messageResponse.setCode(0);
            messageResponse.setType("region");
            messageResponse.setTitle("Region has been created");
        }else{

            messageResponse.setCode(1);
            messageResponse.setType("region");
            messageResponse.setTitle("Region has not created");
        }

        return messageResponse;
    }
}
