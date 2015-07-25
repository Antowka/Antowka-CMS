package ru.antowka.dao;

import ru.antowka.entity.User;

/**
 * Created by anton on 25.07.15.
 */
public interface UserDao {

    public boolean addUser(User user);
    public boolean updateUser(User user);
    public User getUserById(long userId);
    public User getUserByLogin(String login);
    public User getUserByEmail(String email);
    public boolean deleteUser(User user);
}
