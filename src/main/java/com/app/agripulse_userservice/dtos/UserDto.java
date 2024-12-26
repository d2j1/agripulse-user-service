package com.app.agripulse_userservice.dtos;

import com.app.agripulse_userservice.models.Role;
import com.app.agripulse_userservice.models.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private String name;
    private String email;
    private String mobileNo;
    private String countryCode;
    private List<Role> roles;

    public static UserDto fromUser(UserModel user) {
        UserDto userDto = new UserDto();
        userDto.name = user.getName();
        userDto.email = user.getUsername();
        userDto.mobileNo = user.getMobileNo();
        userDto.countryCode = user.getCountryCode();
        userDto.roles = user.getRoles();

        return userDto;
    }
}
