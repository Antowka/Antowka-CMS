package ru.antowka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.antowka.form.FileUploadForm;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Anton Nik on 12.08.15.
 */
@Controller
public class FileController {

    private String storagePath;

    /**
     *  **************************** Getters and Setters ************************************
     */

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }


    /**
     *  **************************** Functionality methods **********************************
     */

    /**
     * Upload files
     * @param uploadForm
     * @param map
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    @ResponseBody
    public String save(@ModelAttribute("uploadFiles") FileUploadForm uploadForm, Model map)
            throws IllegalStateException, IOException, NoSuchAlgorithmException {
        List<MultipartFile> files = uploadForm.getFiles();

        List<String> fileNames = new ArrayList<String>();

        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {

                String fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);


                if (!"".equalsIgnoreCase(fileName)) {

                    //Generate MD5 for new file
                    byte[] hashFile = MessageDigest.getInstance("MD5").digest(multipartFile.getBytes());
                    StringBuilder md5File = new StringBuilder();
                    for(byte b: hashFile) {
                        md5File.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
                    }

                    //Create path for new file
                    String path = generatePathForFile(md5File.toString());

                    //Save file
                    String extension=multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                    String fileFullPath = storagePath + path + md5File.toString() + extension;
                    multipartFile.transferTo(new File(fileFullPath));

                    fileNames.add(fileName);
                }
            }
        }

        map.addAttribute("files", fileNames);
        return "file_upload_success";
    }

    /**
     * Method generate path for file and create dirs
     *
     * @param hashFile
     * @return
     */
    private String generatePathForFile(String hashFile){

        String path = hashFile;

        return path;
    }


}
