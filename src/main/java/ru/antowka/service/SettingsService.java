package ru.antowka.service;

import ru.antowka.entity.Setting;

import java.util.Map;

/**
 * Interface for SettingsService
 */
public interface SettingsService {

    /**
     * Method response setting by name
     *
     * @param settingName
     * @return
     */
    Setting getSetting(String settingName);

    /**
     * Method response settings by settingNames array
     *
     * @param settingsName
     * @return
     */
    Map<String, String> getSettings(String[] settingsName);

    /**
     * Update setting
     *
     * @param setting
     * @return
     */
    Setting updateSetting(Setting setting);
}
