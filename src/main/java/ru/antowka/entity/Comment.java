package ru.antowka.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Anton Nik on 09.09.15.
 */
@Entity
public class Comment implements Serializable {

    private int commentId;
    private String title;
    private String description;
    private String creationDate;

    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="ticketId")
    @JsonIdentityReference(alwaysAsId=true)
    private Ticket ticket;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
