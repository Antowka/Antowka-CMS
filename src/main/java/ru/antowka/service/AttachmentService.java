package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.antowka.dao.AttachmentDao;
import ru.antowka.entity.Attachment;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.factory.AttachmentFactory;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

    @Autowired
    private AttachmentFactory attachmentFactory;

    @Autowired
    private Attachment attachment;

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
     * @return
     */
    public MessageResponse createAttachments(List<MultipartFile> files) {
        
        //save files

        List<Attachment> attachments = new ArrayList<Attachment>();

        try {
            attachments = saveFiles(files);

        } catch (IOException | NoSuchAlgorithmException e) {

            messageResponse.setCode(0);
            messageResponse.setTitle("Attachment has not been saved");
            messageResponse.setType("Attachment");
            messageResponse.setMessage("You attachments was not add to system");

            return messageResponse;

        }


        //Response save result
        if(attachments.size() > 0) {

            List<Integer> attachmentIds = attachmentDao.createAttachments(attachments);

            if(attachmentIds.size() > 0){

                messageResponse.setCode(1);
                messageResponse.setTitle("Successful");
                messageResponse.setType("Attachment");
                messageResponse.setMessage("You attachments added to system");
                messageResponse.setParams(attachmentIds);

            } else{

                messageResponse.setCode(0);
                messageResponse.setTitle("Attachment has not been saved");
                messageResponse.setType("Attachment");
                messageResponse.setMessage("You attachments was not add to system");
            }
        }else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Attachment has not been saved");
            messageResponse.setType("Attachment");
            messageResponse.setMessage("Not found upload files");
        }

        return messageResponse;
    }

    /**
     * Save files in fs
     *
     * @param files
     * @return
     */
    private List<Attachment> saveFiles(List<MultipartFile> files) throws IllegalStateException, IOException, NoSuchAlgorithmException {

        List<Attachment> attachments = new ArrayList<Attachment>();

        if(null != files && files.size() > 0) {

            for (MultipartFile multipartFile : files) {

                String fileName = multipartFile.getOriginalFilename();

                if (!"".equalsIgnoreCase(fileName)) {

                    //Generate MD5 for new file
                    byte[] hashFile = MessageDigest.getInstance("MD5").digest(multipartFile.getBytes());

                    StringBuilder md5File = new StringBuilder();
                    for(byte b: hashFile) {
                        md5File.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
                    }

                    //Create path for new file
                    String extension=multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                    String path = generatePathForFile(md5File.toString(), extension);

                    //Save file
                    File file = new File(path);

                    if(file.mkdirs()) {
                        //Save file to FS
                        multipartFile.transferTo(file);

                        //Create entity Attachment for new file;
                        attachmentFactory.newAttachment();
                        attachment.setRealFileName(fileName);
                        attachment.setFilePathInStorage(path);
                        attachment.setFileSize(multipartFile.getSize());
                        attachment.setMimeType(multipartFile.getContentType());

                        attachments.add(attachment);
                    }
                }
            }
        }

        return attachments;
    }

    /**
     * Method generate path for file and create dirs
     *
     * @param hashFile
     * @return
     */
    private String generatePathForFile(String hashFile, String extension){

        String folder1 = hashFile.substring(0, 2);
        String folder2 = hashFile.substring(2, 4);

        return  storagePath + folder1 + "/" + folder2 + "/" + hashFile  + extension;
    }

}
