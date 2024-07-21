package com.app.agripulse_userservice.controller;

import com.app.agripulse_userservice.dtos.ConfirmationDto;
import com.app.agripulse_userservice.dtos.PasswordDto;
import com.app.agripulse_userservice.dtos.UserDto;
import com.app.agripulse_userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
       UserDto updatedUser = userService.updateUser(id, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/updatedPassword")
    public ResponseEntity<ConfirmationDto> passwordUpdate(@RequestBody PasswordDto passwordDto){
            ConfirmationDto confirm = userService.updateUserPassword(passwordDto);

            return new ResponseEntity<>(confirm, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConfirmationDto> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(new ConfirmationDto(id, "User deleted successfully"), HttpStatus.OK);
    }

}
