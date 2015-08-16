package ru.antowka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.antowka.form.FileUploadForm;
import ru.antowka.service.AttachmentService;

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
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;


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
                    String extension=multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                    String path = attachmentService.generatePathForFile(md5File.toString(), extension);

                    //Save file
                    File file = new File(path);
                    if(file.mkdirs()) {
                        multipartFile.transferTo(file);
                    }

                    fileNames.add(path);
                }
            }
        }

        map.addAttribute("files", fileNames);
        return "file_upload_success";
    }
}
