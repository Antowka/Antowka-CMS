package ru.antowka.dao;

import ru.antowka.entity.Attachment;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by Anton Nik on 16.08.15.
 */
public interface AttachmentDao {

    Attachment findAttachmentById(int attachmentId);
    List<Attachment> getAttachmentByUser(User user);
    List<Integer> createAttachments(List<Attachment> attachments);
}
