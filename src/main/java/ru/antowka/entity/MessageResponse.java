package ru.antowka.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by anton on 09.08.15.
 */

public class MessageResponse implements Serializable{

    private int code;
    private String title;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
