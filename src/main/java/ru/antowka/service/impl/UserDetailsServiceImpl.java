package ru.antowka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.antowka.dao.UserDao;
import ru.antowka.entity.UserRole;
import ru.antowka.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anton on 25.07.15.
 */
@Service("myUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;


    /**
     *  ******************************** Simple methods ****************************************
     */

    /**
     * Method response current authorized user
     *
     * @return User
     */
    public ru.antowka.entity.User getAuthorizedUser(){

        String login = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                       .getAuthentication()
                       .getPrincipal())
                       .getUsername();

        return userDao.findByUserName(login);
    }


    /**
     *  ***************************** Methods for security *************************************
     */


    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        ru.antowka.entity.User user = userDao.findByUserName(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());
        return buildUserForAuthentication(user, authorities);
    }



    // Converts our user to org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(ru.antowka.entity.User user, List<GrantedAuthority> authorities) {

        return new User(user.getLogin(), user.getPassword(), user.isEnable(),true, true, true, authorities);
    }



    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<>();
        List<GrantedAuthority> Result = null;
        userRoles.stream().forEach(userRole -> setAuths.add(new SimpleGrantedAuthority(userRole.getUserRole())));
        Result = new ArrayList<GrantedAuthority>(setAuths);
        return Result;
    }
}
