package ru.antowka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.entity.MessageResponse;
import ru.antowka.service.MessageResponseService;

/**
 * Created by anton on 26.12.15.
 */
@Service
public class MessageResponseServiceImpl implements MessageResponseService{

    @Autowired
    MessageResponse messageResponse;

    /**
     * Method create response on remove action for any entity
     *
     * @param isRemoved
     * @param entityName
     * @param entityId
     * @return
     */
    public MessageResponse getResponseForRemoveEntity(boolean isRemoved, String entityName, int entityId){

        if(isRemoved){

            messageResponse.setCode(1);
            messageResponse.setTitle("Successful removed");
            messageResponse.setMessage("Your " + entityName + " #" + entityId + " removed from system");
        }else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Category has not been removed");
            messageResponse.setMessage("Your " + entityName + " #" + entityId + " removed to system");
        }

        return messageResponse;
    }

    /**
     * Method create response on update entity
     *
     * @param isUpdated
     * @param entityName
     * @param entityId
     * @return
     */
    public MessageResponse getResponseForUpdateEntity(boolean isUpdated, String entityName, int entityId){

        if(isUpdated){

            messageResponse.setCode(1);
            messageResponse.setTitle("Successful updated");
            messageResponse.setMessage("Your " + entityName + " #" + entityId + " updated in system");
        }else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Category has not been removed");
            messageResponse.setMessage("Your " + entityName + " #" + entityId + " has not update in system");
        }

        return messageResponse;
    }

    /**
     * Method create response on create Entity
     *
     * @param isCreated
     * @param entityName
     * @param entityId
     * @return
     */
    public MessageResponse getResponseForCreateEntity(boolean isCreated, String entityName, int entityId){

        if(isCreated){

            messageResponse.setCode(1);
            messageResponse.setTitle("Successful created");
            messageResponse.setMessage("Your " + entityName + " #" + entityId + " created in system");
        }else{

            messageResponse.setCode(0);
            messageResponse.setTitle("Category has not been created");
            messageResponse.setMessage("Your " + entityName + " has not create in system");
        }

        return messageResponse;
    }
}
