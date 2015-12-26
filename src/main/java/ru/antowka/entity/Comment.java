package ru.antowka.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * Created by Anton Nik on 09.09.15.
 */
@Entity
public class Comment implements Serializable {

    private int commentId;
    private String title;
    private String description;
    private String creationDate;
    private User user;
    private boolean isDeleted;
    private Set<Attachment> attachments;

    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="ticketId")
    @JsonIdentityReference(alwaysAsId=true)
    private Ticket ticket;

    /**
     * Method response entity name
     *
     * @return
     */
    public String getEntityName() {
        return "Comment";
    }

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

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
