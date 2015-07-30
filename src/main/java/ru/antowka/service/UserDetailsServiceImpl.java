package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.antowka.dao.UserDao;
import ru.antowka.entity.User;
import ru.antowka.entity.enums.UserRoleEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by anton on 25.07.15.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserDao userDao;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // с помощью нашего сервиса UserService получаем User
        User user = userDao.getUserByLogin(username);

        // указываем роли для этого пользователя
        Set<GrantedAuthority> roles = new HashSet<>(0);

        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        // на основании полученныйх даных формируем объект UserDetails
        // который позволит проверить введеный пользователем логин и пароль
        // и уже потом аутентифицировать пользователя
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                roles
        );

        return userDetails;
    }
}
