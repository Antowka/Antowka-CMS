package ru.antowka.entity;

import java.io.Serializable;

/**
 * Created by anton on 09.08.15.
 */
public class TicketStatus implements Serializable{

    private int ticketsStatusId;
    private String status;
    private boolean publicStatus;

    /**
     * Method response entity name
     *
     * @return
     */
    public String getEntityName() {
        return "TicketStatus";
    }

    public int getTicketsStatusId() {
        return ticketsStatusId;
    }

    public void setTicketsStatusId(int ticketsStatusId) {
        this.ticketsStatusId = ticketsStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(boolean publicStatus) {
        this.publicStatus = publicStatus;
    }
}
