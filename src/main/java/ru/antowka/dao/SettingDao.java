package ru.antowka.dao;

import ru.antowka.entity.Setting;

/**
 * Created by anton on 03.08.15.
 */
public interface SettingDao {

    Setting findSettingByName(String settingName);

    Setting findSettingById(int settingId);
}
