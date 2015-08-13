package ru.antowka.entity;

import java.io.Serializable;

/**
 * Created by Anton Nik on 13.08.15.
 */
public class File implements Serializable{

    private int fileId;
    private String realFileName;
    private String filePathInStorage;
    private Float fileSize;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
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
}
