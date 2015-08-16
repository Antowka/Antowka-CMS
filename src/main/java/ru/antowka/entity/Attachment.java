package ru.antowka.entity;

import java.io.Serializable;

/**
 * Created by Anton Nik on 13.08.15.
 */
public class Attachment implements Serializable{

    private int attachmentId;
    private int userOwnerId;
    private String realFileName;
    private String filePathInStorage;
    private Float fileSize;

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

    public String getFilePathInStorage() {
        return filePathInStorage;
    }

    public void setFilePathInStorage(String filePathInStorage) {
        this.filePathInStorage = filePathInStorage;
    }

    public Float getFileSize() {
        return fileSize;
    }

    public void setFileSize(Float fileSize) {
        this.fileSize = fileSize;
    }

    public int getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(int userOwnerId) {
        this.userOwnerId = userOwnerId;
    }
}
