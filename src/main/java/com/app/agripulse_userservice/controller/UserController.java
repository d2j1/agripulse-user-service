package com.app.agripulse_userservice.controller;

import com.app.agripulse_userservice.auth.OtpAuthenticationToken;
import com.app.agripulse_userservice.dtos.ConfirmationDto;
import com.app.agripulse_userservice.dtos.PasswordDto;
import com.app.agripulse_userservice.dtos.UserDto;
import com.app.agripulse_userservice.models.LoginRequest;
import com.app.agripulse_userservice.models.OtpRequest;
import com.app.agripulse_userservice.models.UserModel;
import com.app.agripulse_userservice.service.OtpService;
import com.app.agripulse_userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final AuthenticationManager authenticationManager;
    private final OtpService otpService;
    private final UserService userService;

    public UserController( AuthenticationManager authenticationManager, OtpService otpService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.otpService = otpService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto userDto){
        UserDto savedUser= userService.createUser(userDto);

        if(savedUser == null){
            return new ResponseEntity<>(savedUser, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/generateotp")
    public ResponseEntity<String> generateOtp(@RequestBody OtpRequest otpRequest){

        String otp = otpService.generateOtp(otpRequest.getMobileNo());
        return new ResponseEntity<>(otp, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){

        try {
            // Create an instance of OtpAuthenticationToken with mobileNo and otp
            UserDto user = userService.getUserByMobileNo(loginRequest.getMobileNo());

            OtpAuthenticationToken otpAuthenticationToken = new OtpAuthenticationToken(
                    loginRequest.getMobileNo(),
                    loginRequest.getOtp(),
                    Collections.singletonList(new SimpleGrantedAuthority(user.getRoles().toString()))
            );

            // Authenticate the token
            Authentication authentication = authenticationManager.authenticate(otpAuthenticationToken);

            // If authentication is successful, return a success response
            return new ResponseEntity<>("Authenticated Successfully", HttpStatus.OK);

        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
        }
    }

    /*
    remove below endpoints
     */
//    @GetMapping("/")
//    public ResponseEntity<List<UserDto>> getUsers() {
//        List<UserDto> users = userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
//        UserDto user = userService.getUserById(id);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
//        UserDto savedUser = userService.createUser(userDto);
//        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
//       UserDto updatedUser = userService.updateUser(id, userDto);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
//
//    @PutMapping("/updatedPassword")
//    public ResponseEntity<ConfirmationDto> passwordUpdate(@RequestBody PasswordDto passwordDto){
//            ConfirmationDto confirm = userService.updateUserPassword(passwordDto);
//
//            return new ResponseEntity<>(confirm, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ConfirmationDto> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//
//        return new ResponseEntity<>(new ConfirmationDto(id, "User deleted successfully"), HttpStatus.OK);
//    }

}
