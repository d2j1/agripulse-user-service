package com.app.agripulse_userservice.config;

import com.app.agripulse_userservice.models.Role;

import com.app.agripulse_userservice.models.UserModel;
import com.app.agripulse_userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserModel> userOpt  = userRepository.findByEmail(username);
        UserModel user = userOpt.orElseThrow();

        List<Role> roles = user.getRoles();

        return new User(user.getEmail(), user.getPassword(), roles);
    }
}