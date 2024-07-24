package com.app.agripulse_userservice.dtos;

import com.app.agripulse_userservice.models.Role;
import com.app.agripulse_userservice.models.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoggedInUserDto {

    private String name;
    private String email;
    private List<Role> roles;


    public static LoggedInUserDto fromUser(UserModel user) {
        LoggedInUserDto userDto = new LoggedInUserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getUsername());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
