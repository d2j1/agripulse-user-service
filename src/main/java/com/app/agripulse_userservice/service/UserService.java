package com.app.agripulse_userservice.service;

import com.app.agripulse_userservice.dtos.ConfirmationDto;
import com.app.agripulse_userservice.dtos.PasswordDto;
import com.app.agripulse_userservice.dtos.UserDto;


import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    UserDto createUser(UserDto userDto);
    ConfirmationDto updateUserPassword(PasswordDto passwordDto);
    void deleteUser(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, UserDto userDto);

}
