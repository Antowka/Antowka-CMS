package ru.antowka.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.antowka.dao.UserDao;
import ru.antowka.entity.UserRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anton on 25.07.15.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    /**
     *   ***********************Getters and Setters *****************************
     */

    public UserDao getUserDao() {
        return userDao;
    }


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }



    /**
     *  ***************************** Logic *************************************
     */


    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        ru.antowka.entity.User user = userDao.findByUserName(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());

        return buildUserForAuthentication(user, authorities);
    }



    // Converts our user to org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(ru.antowka.entity.User user, List<GrantedAuthority> authorities) {

        return new User(user.getLogin(), user.getPassword(), user.isEnable(),true, true, true, authorities);
    }



    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        List<GrantedAuthority> Result = null;

        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }
}
