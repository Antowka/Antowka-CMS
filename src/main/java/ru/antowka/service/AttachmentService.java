package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.AttachmentDao;

/**
 * Created by anton on 16.08.15.
 */
@Service
public class AttachmentService {

    @Autowired
    private AttachmentDao attachmentDao;


    private String storagePath;

    /**
     *  **************************** Getters and Setters ************************************
     */

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * Method generate path for file and create dirs
     *
     * @param hashFile
     * @return
     */
    public String generatePathForFile(String hashFile, String extension){

        String folder1 = hashFile.substring(0, 2);
        String folder2 = hashFile.substring(2, 4);

        return  storagePath + folder1 + "/" + folder2 + "/" + hashFile  + extension;
    }

}
