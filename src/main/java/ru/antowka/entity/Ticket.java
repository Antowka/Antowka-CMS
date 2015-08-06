package ru.antowka.entity;

import java.util.Set;

/**
 * Created by anton on 06.08.15.
 */
public class Ticket {

    private int ticketId;
    private int userOwnerId;
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private String description;
    private String creationDate;
    private Set<TicketCategory> categories;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(int userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Set<TicketCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<TicketCategory> categories) {
        this.categories = categories;
    }
}
