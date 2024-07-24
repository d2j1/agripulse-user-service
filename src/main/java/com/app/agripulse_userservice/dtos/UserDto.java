package com.app.agripulse_userservice.dtos;

import com.app.agripulse_userservice.models.UserModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String name;
    private String email;
    private String password;

    public static UserDto fromUser(UserModel user) {
        UserDto userDto = new UserDto();
        userDto.name = user.getName();
        userDto.email = user.getUsername();
        userDto.email = user.getUsername();
        userDto.password = user.getPassword();
        return userDto;
    }
}
