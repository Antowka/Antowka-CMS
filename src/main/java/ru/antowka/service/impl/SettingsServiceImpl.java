package ru.antowka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.SettingDao;
import ru.antowka.entity.Setting;
import ru.antowka.service.SettingsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for Settings
 */

@Service
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private SettingDao settingDao;


    public Setting getSetting(String settingName){

        Setting setting = null;

        setting = settingDao.findSettingByName(settingName);

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

    public Setting updateSetting(Setting setting){
        return settingDao.updateSetting(setting);
    }
}
