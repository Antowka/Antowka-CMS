package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.AttachmentDao;
import ru.antowka.entity.Attachment;
import ru.antowka.entity.MessageResponse;

import java.util.List;

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
     * @param attachments
     * @return
     */
    public MessageResponse createAttachments(List<Attachment> attachments){

        if(attachments.size() > 0) {

            List<Integer> attachmentIds = attachmentDao.createAttachments(attachments);

            if(attachmentIds.size() > 0){

                messageResponse.setCode(1);
                messageResponse.setTitle("Successful");
                messageResponse.setMessage("You attachments added to system");
            } else{

                messageResponse.setCode(0);
                messageResponse.setTitle("Attachment has not been saved");
                messageResponse.setMessage("You attachments was not add to system");
            }
        }else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Attachment has not been saved");
            messageResponse.setMessage("Not found upload files");
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
