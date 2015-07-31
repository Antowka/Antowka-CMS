package ru.antowka.dao;

import ru.antowka.entity.User;

/**
 * Created by anton on 25.07.15.
 */
public interface UserDao {

    User findByUserName(String username);
}
