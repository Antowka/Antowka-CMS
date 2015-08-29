package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.dao.RegionDao;
import ru.antowka.entity.Region;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anton Nik on 28.08.15.
 */
@Repository
public class RegionDaoImpl implements RegionDao {

    @Autowired
    private HibernateSessionFactory hibernateSessionFactory;

    @Override
    @Transactional
    public Region createRegion(Region region) {

        Session session = hibernateSessionFactory.getSession();
        region.setRegionId((int)session.save(region));
        return region;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Region> getAllRegions() {

        Session session = hibernateSessionFactory.getSession();
        return (List<Region>)session.createCriteria(Region.class)
                                                    .addOrder(Order.desc("level"))
                                                    .list();
    }

    @Override
    @Transactional
    public List<Region> getAllRegionsByLevel(int level) {
        return null;
    }

    @Override
    @Transactional
    public Region editRegion(Region region) {
        return null;
    }

    @Override
    @Transactional
    public boolean removeRegion(int regionId) {
        return false;
    }
}
