package com.aryan.splitify.Services;

import com.aryan.splitify.Entity.User;
import com.aryan.splitify.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Collections;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);  // 🔥 Use findByEmail, not findByUserName
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())          // ✅ use email
                .password(user.getPassword())       // ✅ password
                .authorities(Collections.emptyList())  // ✅ no roles, empty authorities
                .build();
    }
}
