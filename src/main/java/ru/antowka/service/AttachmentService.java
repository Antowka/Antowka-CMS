package ru.antowka.service;

import org.springframework.web.multipart.MultipartFile;
import ru.antowka.entity.MessageResponse;

import java.util.List;

/**
 * Interface for AttachmentService
 */
public interface AttachmentService {

    void setStoragePath(String storagePath);

    void setPathDefaultPreview(String pathDefaultPreview);

    MessageResponse createAttachment(List<MultipartFile> files);

    MessageResponse removeAttachment(int attachmentId);
}
