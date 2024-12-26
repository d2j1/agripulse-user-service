package com.app.agripulse_userservice.serviceimpl;

import com.app.agripulse_userservice.dtos.ConfirmationDto;
import com.app.agripulse_userservice.dtos.PasswordDto;
import com.app.agripulse_userservice.dtos.UserDto;
import com.app.agripulse_userservice.exceptions.IncorrectPassswordException;
import com.app.agripulse_userservice.exceptions.UserNotFoundException;
import com.app.agripulse_userservice.models.UserModel;
import com.app.agripulse_userservice.repository.UserRepository;
import com.app.agripulse_userservice.service.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDto createUser(UserDto userDto) {

        UserModel user = new UserModel();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setMobileNo(userDto.getMobileNo());
        user.setCountryCode(userDto.getCountryCode());
        UserModel savedUser = userRepository.save(user);

        return UserDto.fromUser(savedUser);
    }

    @Override
    public UserDto getUserByMobileNo(String mobileNo) {
        Optional<UserModel> user = userRepository.findByMobileNo(mobileNo);

        if(user.isEmpty()){
            return null;
        }

        return UserDto.fromUser(user.get());
    }
}
