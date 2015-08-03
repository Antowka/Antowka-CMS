package ru.antowka.entity;

/**
 * Created by anton on 03.08.15.
 */
public class Setting {

    private String settingName;
    private String value;

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
