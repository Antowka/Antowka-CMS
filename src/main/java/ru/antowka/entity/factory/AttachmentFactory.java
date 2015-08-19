package ru.antowka.entity.factory;

import ru.antowka.entity.Attachment;

/**
 * Created by Anton Nik on 19.08.15.
 */
public abstract class AttachmentFactory {

    /**
     * Create new Instance Attachment
     * @return
     */
    public abstract Attachment newAttachment();
}
