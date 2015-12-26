package ru.antowka.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Anton Nik on 27.08.15.
 */
public class Region implements Serializable{

    private int regionId;
    private int parentId;
    private int level;
    private String title;
    private String description;
    private List<Region> childRegions = new ArrayList<Region>();

    /**
     * Method response entity name
     *
     * @return
     */
    public String getEntityName() {
        return "Region";
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Region> getChildRegions() {
        return childRegions;
    }

    public void setChildRegions(List<Region> childRegions) {
        this.childRegions = childRegions;
    }

    public void addChildRegion(Region region){
        childRegions.add(region);
    }
}
