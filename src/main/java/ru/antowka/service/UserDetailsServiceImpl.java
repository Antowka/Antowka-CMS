package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.antowka.dao.UserDao;
import ru.antowka.entity.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anton on 25.07.15.
 */
@Service("myUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;


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



    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<>();
        List<GrantedAuthority> Result = null;
        userRoles.stream().forEach(userRole -> setAuths.add(new SimpleGrantedAuthority(userRole.getRole())));
        Result = new ArrayList<GrantedAuthority>(setAuths);
        return Result;
    }
}
