package ru.antowka.entity;

/**
 * Created by anton on 03.08.15.
 */
public class Setting {

    private int setingId;
    private String settingname;
    private int value;

    public int getSetingId() {
        return setingId;
    }

    public void setSetingId(int setingId) {
        this.setingId = setingId;
    }

    public String getSettingname() {
        return settingname;
    }

    public void setSettingname(String settingname) {
        this.settingname = settingname;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
