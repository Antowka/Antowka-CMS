package ru.antowka.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import ru.antowka.entity.Setting;
import ru.antowka.service.TicketService;
import ru.antowka.service.impl.SettingsServiceImpl;
import ru.antowka.service.impl.TicketServiceImpl;

/**
 * Job for works with with Tickets by scheduler
 */
public class Tickets {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SettingsServiceImpl settingsService;

    @Autowired
    private Setting setting;

    @Async
    public void updateCounterInSettings() {

        //update counter public tickets
        System.out.println("CRON EXECUTE!");
        setting.setSettingName("site_ticket_counter");
        String counter = ticketService.countPublicTickets().toString();
        setting.setValue(counter);
        settingsService.updateSetting(setting);
    }
}
