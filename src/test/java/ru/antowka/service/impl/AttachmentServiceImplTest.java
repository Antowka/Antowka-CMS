package ru.antowka.service.impl;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.antowka.entity.MessageResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Test for AttachmentService
 */
public class AttachmentServiceImplTest {

    @InjectMocks
    private AttachmentServiceImpl attachmentService;

    private MultipartFile multipartFile;

    private String pathTempFile = System.getProperty("java.io.tmpdir") + "/test_" + Math.random() +  ".png";

    @Before
    public void setUp() throws Exception {

        //Download file for test
        URL website = new URL("http://archive.org/web/images/logo_wayback_210x77.png");

        ReadableByteChannel resFile = Channels.newChannel(website.openStream());

        //Create file
        File file = new File(pathTempFile);
        FileOutputStream fos = new FileOutputStream(pathTempFile);
        fos.getChannel().transferFrom(resFile, 0, Long.MAX_VALUE);

        //Save to MultipartFile
        FileInputStream input = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file",
                                        file.getName(), "image/png", IOUtils.toByteArray(input));

        fos.close();
        input.close();
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testCreateAttachment() throws Exception {
        String test123 = "";
    }

    @Test
    public void testRemoveAttachment() throws Exception {

    }
}