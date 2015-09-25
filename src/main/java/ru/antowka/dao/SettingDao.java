package ru.antowka.dao;

import ru.antowka.entity.Setting;

import java.util.List;

/**
 * Created by anton on 03.08.15.
 */
public interface SettingDao {

    Setting findSettingByName(String settingName);
    List<Setting> findSettingsByName(String[] settingsName);
    Setting updateSetting(Setting setting);
}
