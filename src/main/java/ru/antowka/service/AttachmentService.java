package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.AttachmentDao;
import ru.antowka.entity.Attachment;
import ru.antowka.entity.MessageResponse;

/**
 * Created by Anton Nik on 16.08.15.
 */
@Service
public class AttachmentService {

    @Autowired
    private AttachmentDao attachmentDao;

    @Autowired
    private MessageResponse messageResponse;

    private String storagePath;

    /**
     *  **************************** Getters and Setters ************************************
     */

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }


    /**
     * ***************************** Functionality methods **********************************
     */


    /**
     * Create attachment
     *
     * @param attachment
     * @return
     */
    public MessageResponse createAttachment(Attachment attachment){

        Integer attachmentId = attachmentDao.createAttachment(attachment);

        if(!attachmentId.equals(0)){

            messageResponse.setCode(1);
            messageResponse.setTitle("Successful");
            messageResponse.setMessage("You attachment added to system with #"+attachmentId);
        } else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Ticket has not been saved");
            messageResponse.setMessage("You ticket was not add to system with #"+attachmentId);
        }

        return messageResponse;
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
