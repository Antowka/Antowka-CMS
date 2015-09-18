package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ru.antowka.dao.SettingDao;
import ru.antowka.entity.Setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anton on 04.08.15.
 */

@Service
public class SettingsService {

    @Autowired
    private SettingDao settingDao;

    public Setting getSetting(String settingname){

        Setting setting = null;

        setting = settingDao.findSettingByName(settingname);

        return setting;
    }

    public Map<String, String> getSettings(String[] settingsName){

        List<Setting> settingsList = null;

        settingsList = settingDao.findSettingsByName(settingsName);

        Map<String, String> settings = new HashMap<String, String>();

        settingsList.stream()
                .forEach(setting -> settings.put(setting.getSettingName(), setting.getValue()));

        return settings;
    }
}
