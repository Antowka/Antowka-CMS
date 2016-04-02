package ru.antowka.service;

/**
 * Interface for send alerts on emails
 */
public interface EmailSenderService {

    /**
     * Alert about public ticket
     *
     * @param email
     */
    void ticketPublic(String email);

    /**
     * Alert about new ticket
     *
     * @param email
     */
    void newTicketCreated(String email);


}
