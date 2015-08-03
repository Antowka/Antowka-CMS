package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.dao.SettingDao;
import ru.antowka.entity.Setting;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anton Nik  on 03.08.15.
 */
@Repository
public class SettingDaoImpl implements SettingDao {

    @Autowired
    public HibernateSessionFactory hibernateSessionFactory;

    @Override
    @Transactional
    public Setting findSettingByName(String settingName) {

        Setting setting = null;

        Session session = hibernateSessionFactory.getSession();
        setting = (Setting) session.get(Setting.class, settingName);

        return setting;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Setting> findSettingsByName(String[] settingsName){

        List<Setting> settings = null;
        Session session = hibernateSessionFactory.getSession();
        settings = (List<Setting>) session.createCriteria(Setting.class)
                                          .add(Restrictions.in("settingName", settingsName))
                                          .list();


        return settings;
    }
}
