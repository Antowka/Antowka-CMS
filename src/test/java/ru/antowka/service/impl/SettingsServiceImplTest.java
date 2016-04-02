package ru.antowka.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.antowka.dao.SettingDao;
import ru.antowka.entity.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test check SettingsService
 */
@RunWith(MockitoJUnitRunner.class)
public class SettingsServiceImplTest {

    @InjectMocks
    SettingsServiceImpl settingsService;

    @Mock
    SettingDao settingDao;

    @Test
    public void getSetting() throws Exception {

        Setting setting = new Setting();
        setting.setSettingName("Ticket Limit");
        setting.setValue("100");

        Mockito.when(settingDao.findSettingByName("Ticket Limit")).thenReturn(setting);

        setting = settingsService.getSetting("Ticket Limit");

        assertEquals(setting.getSettingName(), "Ticket Limit");
    }

    @Test
    public void getSettings() throws Exception {

        //Start params
        String[] settingsArray = {"Ticket Limit", "Send Timeout", "Page Limit"};

        //Create response
        List<Setting> settingList = new ArrayList<>();

        for(int i=0; i<settingsArray.length; i++){
            Setting setting = new Setting();
            setting.setSettingName(settingsArray[i]);
            setting.setValue(String.valueOf(i*100));
            settingList.add(setting);
        }

        Mockito.when(settingDao.findSettingsByName(settingsArray)).thenReturn(settingList);

        Map<String, String> settings = settingsService.getSettings(settingsArray);

        assertEquals(settings.get("Send Timeout"), "100");
    }

    @Test
    public void updateSetting() throws Exception {

        Setting settingUpdated = new Setting();
        settingUpdated.setSettingName("DemoSetting");
        settingUpdated.setValue("500");

        Setting settingStart = new Setting();
        settingStart.setSettingName("DemoSetting");
        settingStart.setValue("100");

        Mockito.when(settingDao.updateSetting(settingStart)).thenReturn(settingUpdated);

        Setting resultSetting = settingsService.updateSetting(settingStart);

        assertEquals(resultSetting.getValue(), "500");
    }
}