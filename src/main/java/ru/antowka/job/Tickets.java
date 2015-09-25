package ru.antowka.job;

import org.springframework.beans.factory.annotation.Autowired;
import ru.antowka.entity.Setting;
import ru.antowka.service.SettingsService;
import ru.antowka.service.TicketService;

/**
 * Created by Anton Nik on 06.09.15.
 */
public class Tickets {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private Setting setting;

    public void updateCounterInSettings(){

        //update counter public tickets
        System.out.println("CRON EXECUTE!");
        setting.setSettingName("site_ticket_counter");
        String counter = ticketService.countPublicTickets().toString();
        setting.setValue(counter);
        settingsService.updateSetting(setting);
    }
}
