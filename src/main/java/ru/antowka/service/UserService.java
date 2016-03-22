package ru.antowka.service;

import ru.antowka.entity.User;

/**
 * Interface for userServices
 */
public interface UserService {

    /**
     * Method response current authorized user
     *
     * @return User
     */
    User getAuthorizedUser();
}
