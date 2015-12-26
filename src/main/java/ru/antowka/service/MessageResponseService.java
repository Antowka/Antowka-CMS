package ru.antowka.service;

import ru.antowka.entity.MessageResponse;

/**
 * Created by Anton Nik on 26.12.15.
 */
public interface MessageResponseService {

    MessageResponse getResponseForRemoveEntity(boolean isRemoved, String entityName, int entityId);

    MessageResponse getResponseForUpdateEntity(boolean isUpdated, String entityName, int entityId);

    MessageResponse getResponseForCreateEntity(boolean isCreated, String entityName, int entityId);
}
