package by.training.cryptomarket.service;

import by.training.cryptomarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userService.getUser(login);


        Set<GrantedAuthority> roles = new HashSet();
       roles.add(new SimpleGrantedAuthority(user.getRole()));



        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUserName(),
                        user.getHashOfPassword(),
                        roles);

        return userDetails;
    }

}
