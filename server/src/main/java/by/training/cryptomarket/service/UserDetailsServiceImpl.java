package by.training.cryptomarket.service;

import by.training.cryptomarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceInter userService;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {


        User user = null;
        try {
            user = userService.getUser(login);
        } catch (Exception e) {
          throw new UsernameNotFoundException("user not found");
        }

        Set<GrantedAuthority> roles = new HashSet();
            roles.add(new SimpleGrantedAuthority(user.getRole().toString()));


        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),
                    user.getHashOfPassword(),
                    roles);



        return userDetails;
    }

}
