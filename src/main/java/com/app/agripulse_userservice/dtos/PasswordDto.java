package com.app.agripulse_userservice.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PasswordDto {
    private Long id;
    private String email;
    private String password;
}