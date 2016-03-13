package ru.antowka.service.impl;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.antowka.dao.AttachmentDao;
import ru.antowka.entity.ArticleCategory;
import ru.antowka.entity.Attachment;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.factory.AttachmentFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Test for AttachmentService
 */
@RunWith(PowerMockRunner.class)
public class AttachmentServiceImplTest {

    @InjectMocks
    private AttachmentServiceImpl attachmentService;

    @Mock
    private AttachmentDao attachmentDao;

    @Mock
    private AttachmentFactory attachmentFactory;

    @Captor
    private ArgumentCaptor<List<Attachment>> argumentCaptor;

    private MultipartFile multipartFile;

    private String pathTempFile = System.getProperty("java.io.tmpdir") + "/test_" + Math.random() +  ".png";

    private String storagePath = System.getProperty("java.io.tmpdir") + "/STORAGE/";

    private String pathDefaultPreview = storagePath + "preview/";

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

        //Set paths
        attachmentService.setStoragePath(storagePath);
        attachmentService.setPathDefaultPreview(pathDefaultPreview);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Check uploading files and create attachments
     *
     * @throws Exception
     */
    @Test
    public void testCreateAttachment() throws Exception {

        List<MultipartFile> files = new ArrayList<>();
        files.add(multipartFile);

        Mockito.when(attachmentFactory.newAttachment()).thenReturn(null);

        attachmentService.setAttachment(new Attachment());
        attachmentService.setMessageResponse(new MessageResponse());

        MessageResponse messageResponse = attachmentService.createAttachment(files);

        Mockito.verify(attachmentDao, Mockito.times(1)).createAttachments(argumentCaptor.capture());

        Attachment resultAttachment = argumentCaptor.getValue().get(0);

        boolean isExistPreview = Files.exists(Paths.get(storagePath + resultAttachment.getPreviewPath()));
        boolean isExistImage   = Files.exists(Paths.get(storagePath + resultAttachment.getFilePathInStorage()));

        Assert.assertTrue(
                resultAttachment.getMimeType().equals("image/png") &&
                messageResponse.getCode() == 0 &&
                isExistPreview &&
                isExistImage
        );
    }

    @Test
    public void testRemoveAttachment() throws Exception {

    }
}