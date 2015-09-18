package ru.antowka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.Region;
import ru.antowka.service.RegionService;

import java.util.List;

/**
 * Created by Anton Nik on 29.08.15.
 */
@RestController
@RequestMapping("regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    /**
     * Create new Region
     *
     * @param region
     * @return
     */
    @RequestMapping(value = "create-region", method = RequestMethod.POST)
    public @ResponseBody MessageResponse createRegion(@RequestBody Region region){
        return regionService.createRegion(region);
    }

    /**
     * Get All Regions
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getByLevel", method = RequestMethod.GET)
    public @ResponseBody List<Region> getCategoriesAll(org.springframework.web.context.request.WebRequest request){
        return regionService.getRegionsByLevel(Integer.parseInt(request.getParameter("level")));
    }

    /**
     * Get Regions by level
     *
     * link: http://localhost:8080/regions/getAll
     *
     * @return
     */
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public @ResponseBody List<Region> getCategoriesAll(){
        return regionService.getAllRegions();
    }

    /**
     * Remove Region By Id
     * @return
     */
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public @ResponseBody MessageResponse  removeRegion(org.springframework.web.context.request.WebRequest request){
        return regionService.removeRegion(Integer.parseInt(request.getParameter("regionId")));
    }
}
