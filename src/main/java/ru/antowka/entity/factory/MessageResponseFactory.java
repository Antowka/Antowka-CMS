package ru.antowka.entity.factory;

import ru.antowka.entity.MessageResponse;

/**
 * Created by anton on 22.08.15.
 */
public abstract class MessageResponseFactory {

    /**
     * Create new Instance MessageResponse
     * @return
     */
    public abstract MessageResponse newMessage();
}
