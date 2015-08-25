package ru.antowka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.antowka.entity.Attachment;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.factory.AttachmentFactory;
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
    public MessageResponse save(FileUploadForm uploadForm, Model map) throws IOException, NoSuchAlgorithmException {

        return attachmentService.createAttachments(uploadForm.getFiles());
    }
}
