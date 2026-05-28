package com.learn.spring.spring_la_mia_pizzeria_relazioni.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.User;
import com.learn.spring.spring_la_mia_pizzeria_relazioni.repository.UserRepository;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Username errato o inesistente");
        }

        return new DatabaseUserDetails(userOptional.get());
    }

}
