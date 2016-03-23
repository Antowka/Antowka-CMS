package ru.antowka.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import ru.antowka.entity.Setting;
import ru.antowka.service.SettingsService;
import ru.antowka.service.impl.TicketServiceImpl;

/**
 * Created by Anton Nik on 06.09.15.
 */
public class Tickets {

    @Autowired
    private TicketServiceImpl ticketService;

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private Setting setting;

    @Async
    public void updateCounterInSettings(){

        //update counter public tickets
        System.out.println("CRON EXECUTE!");
        setting.setSettingName("site_ticket_counter");
        String counter = ticketService.countPublicTickets().toString();
        setting.setValue(counter);
        settingsService.updateSetting(setting);
    }
}
