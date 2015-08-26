package ru.antowka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antowka.entity.Attachment;
import ru.antowka.entity.MessageResponse;
import ru.antowka.form.FileUploadForm;
import ru.antowka.service.AttachmentService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


/**
 * Created by Anton Nik on 12.08.15.
 */
@Controller
@RequestMapping("attachments")
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

    @RequestMapping(value="upload", method=RequestMethod.POST)
    @ResponseBody
    public MessageResponse save(FileUploadForm uploadForm, Model map) throws IOException, NoSuchAlgorithmException {

        return attachmentService.createAttachment(uploadForm.getFiles());
    }

    /**
     * Remove Attachment without relationship with Tickets or Articles
     *
     * link: http://localhost:8080/attachments/remove?attachmentId=5
     *
     * @param attachment
     * @return
     */
    @RequestMapping(value="remove", method=RequestMethod.GET)
    public @ResponseBody MessageResponse remove(@ModelAttribute Attachment attachment){

        return attachmentService.removeAttachment(attachment.getAttachmentId());
    }
}
