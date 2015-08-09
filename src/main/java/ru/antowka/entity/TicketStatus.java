package ru.antowka.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by anton on 09.08.15.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TicketStatus implements Serializable{

    private int ticketsStatusId;
    private String status;
    private boolean publicStatus;

    @JsonIgnore
    private Set<Ticket> tickets;

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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public boolean isPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(boolean publicStatus) {
        this.publicStatus = publicStatus;
    }
}
