package ru.antowka.entity.form;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Anton Nik on 13.08.15.
 */
public class FileUploadForm {

    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
