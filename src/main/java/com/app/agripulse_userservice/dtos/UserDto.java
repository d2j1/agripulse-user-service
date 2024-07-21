package com.app.agripulse_userservice.dtos;

import com.app.agripulse_userservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String name;
    private String email;
    private String username;
    private String password;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.name = user.getName();
        userDto.email = user.getEmail();
        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        return userDto;
    }
}
