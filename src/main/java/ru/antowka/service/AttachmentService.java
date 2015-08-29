package ru.antowka.service;

import net.coobird.thumbnailator.Thumbnails;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private String pathDefaultPreview;

    /**
     *  **************************** Getters and Setters ************************************
     */

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public void setPathDefaultPreview(String pathDefaultPreview) {
        this.pathDefaultPreview = pathDefaultPreview;
    }

    /**
     * ***************************** Functionality methods **********************************
     */


    /**
     * Create attachment
     *
     * @return
     */
    public MessageResponse createAttachment(List<MultipartFile> files) {
        
        //save files

        List<Attachment> attachments = new ArrayList<Attachment>();

        try {
            attachments = saveFiles(files);

        } catch (IOException | NoSuchAlgorithmException | IllegalStateException e) {

            messageResponse.setCode(0);
            messageResponse.setTitle("Attachment has not been saved");
            messageResponse.setType("Attachment");
            messageResponse.setMessage(e.getMessage());

            return messageResponse;

        }


        //Response save result
        if(attachments.size() > 0) {

            attachments = attachmentDao.createAttachments(attachments);

            if(attachments.size() > 0){

                messageResponse.setCode(1);
                messageResponse.setTitle("Successful");
                messageResponse.setType("Attachment");
                messageResponse.setMessage("You attachments added to system");
                messageResponse.setParams(attachments);
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
     * Remove Attachment by AttachmentId
     *
     * @param attachmentId
     * @return
     */
    public MessageResponse removeAttachment(int attachmentId){

        Attachment attachment = attachmentDao.removeAttachment(attachmentId);
        boolean isDeleted = false;
        List<Attachment> attachments = new ArrayList<Attachment>();

        if(attachment != null) {

            attachments.add(attachment);

            Map<Integer, Boolean> resultStatus = removefilesForAttachment(attachments);

            if(resultStatus.get(attachmentId)){
                isDeleted = true;
            }
        }

        if(isDeleted){

            messageResponse.setCode(1);
            messageResponse.setTitle("Attachment has been removed");
            messageResponse.setType("Attachment");
            messageResponse.setMessage("Your attachment was remove from system");
            messageResponse.setParams(attachments);
        }else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Attachment has been not removed");
            messageResponse.setType("Attachment");
            messageResponse.setMessage("Your attachment was not remove from system");
            messageResponse.setParams(attachments);
        }

        return messageResponse;
    }


    /**
     * ****************************** Inside Methods ******************************************* 
     */
    
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
                    for (byte b : hashFile) {
                        md5File.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
                    }

                    //Create path for new file
                    String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                    String path = generatePathForFile(md5File.toString());

                    //Save file
                    File pathForSave = new File(storagePath + path);
                    File file = new File(storagePath + path + md5File.toString() + extension);

                    //check exist file
                    if (!file.exists()) {

                        //create folders
                        if (pathForSave.exists() || pathForSave.mkdirs()) {

                            //Save file to FS
                            multipartFile.transferTo(file);

                            //Create entity Attachment for new file;
                            attachmentFactory.newAttachment();
                            attachment.setRealFileName(fileName);
                            attachment.setFilePathInStorage(path + md5File.toString() + extension);
                            attachment.setFileSize(multipartFile.getSize());
                            attachment.setMimeType(multipartFile.getContentType());

                            //create preview for image
                            attachment.setPreviewPath(createPreview(path,
                                            md5File.toString(),
                                            extension,
                                            multipartFile.getContentType()
                                    )
                            );

                            attachments.add(attachment);
                        }
                    }else{

                        //if existed file
                        String n = "ds";
                        throw new NoSuchAlgorithmException("File already exist!");
                    }
                }
            }
        }

        return attachments;
    }

    /**
     * Create preview by path origin file
     *
     * @param path
     * @param fileName
     * @param extension
     * @param mimeType
     * @return
     */
    private String createPreview(String path, String fileName, String extension, String mimeType) throws IOException {

        String previewPath =  pathDefaultPreview;

        if (mimeType.startsWith("image/")) {

            previewPath = path + "preview/" + fileName + extension;
            File filePreview = new File(storagePath + path + "preview/");

            if (filePreview.exists() || filePreview.mkdirs()) {

                Thumbnails.of(new File(storagePath + path + fileName + extension))
                        .size(200, 200)
                        .outputQuality(0.8)
                        .toFile(storagePath + previewPath);
            }
        }

        return previewPath;
    }

    /**
     * Method generate path for file and create dirs
     *
     * @param hashFile
     * @return
     */
    private String generatePathForFile(String hashFile){

        String folder1 = hashFile.substring(0, 2);
        String folder2 = hashFile.substring(2, 4);

        return  folder1 + "/" + folder2 + "/";
    }


    /**
     * remove files from FS
     *
     * @param attachments
     * @return
     */
    private Map<Integer, Boolean> removefilesForAttachment(List<Attachment> attachments){

        Map<Integer, Boolean> result = new HashMap<Integer, Boolean>();

        attachments.stream().forEach(attachment -> {

            File file = new File(storagePath + attachment.getFilePathInStorage());

            //delete file
            result.put(attachment.getAttachmentId(), file.delete());

            //check on exist img preview
            if(attachment.getMimeType().startsWith("image/")){
                File preview = new File(storagePath + attachment.getPreviewPath());
                result.replace(attachment.getAttachmentId(), preview.delete());
            }
        });

        return result;
    }
}
