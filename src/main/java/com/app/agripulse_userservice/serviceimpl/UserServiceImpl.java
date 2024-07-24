package com.app.agripulse_userservice.serviceimpl;

import com.app.agripulse_userservice.dtos.ConfirmationDto;
import com.app.agripulse_userservice.dtos.PasswordDto;
import com.app.agripulse_userservice.dtos.UserDto;
import com.app.agripulse_userservice.exceptions.IncorrectPassswordException;
import com.app.agripulse_userservice.exceptions.UserNotFoundException;
import com.app.agripulse_userservice.models.UserModel;
import com.app.agripulse_userservice.repository.UserRepository;
import com.app.agripulse_userservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return user.get();
    }


    @Override
    public UserDto getUserById(Long id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();

            UserDto userDto = new UserDto();
            userDto.setEmail(user.getUsername());
            userDto.setEmail(user.getUsername());

            System.out.println("user mail id is "+user.getEmail());
            return userDto;
        }else{
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        UserModel user = new UserModel();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());

        UserModel savedUser = userRepository.save(user);
        return UserDto.fromUser(savedUser);

    }

    @Override
    public ConfirmationDto updateUserPassword(PasswordDto passwordDto) {
       Optional<UserModel>  userToUpdateOptional = userRepository.findById(passwordDto.getId());

       UserModel userToUpdate = userToUpdateOptional.orElse(null);

       if( userToUpdate != null ) {
           userToUpdate.setPassword( passwordEncoder.encode( passwordDto.getPassword()));
           UserModel updatedUser = userRepository.save(userToUpdate);
           return new ConfirmationDto(updatedUser.getId(), "Password Updated Successfully");
       }else{
            throw new UserNotFoundException("User not found");
       }
    }

    @Override
    public void deleteUser(Long id) {
       try{
           userRepository.deleteById(id);
       }catch (Exception e){
           throw new UserNotFoundException("User not found");
        }

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserModel> users = userRepository.findAll();

        if( users.isEmpty() ){
            throw new UserNotFoundException("User not found");
        }

        List<UserDto> usersDtos = new ArrayList<>();

        for( UserModel user : users ){
            usersDtos.add(UserDto.fromUser(user));
        }
        return usersDtos;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        Optional<UserModel> fetchedUserOptional = userRepository.findById(id);

        if(fetchedUserOptional.isPresent()){
            UserModel fetchedUser = fetchedUserOptional.get();

            fetchedUser.setName(userDto.getName());
            fetchedUser.setEmail(userDto.getEmail());
            fetchedUser.setEmail(userDto.getEmail());
            UserModel updatedUser = userRepository.save(fetchedUser);

            UserDto updatedUserDto = new UserDto();
            updatedUserDto.setEmail(updatedUser.getUsername());
            updatedUserDto.setEmail(updatedUser.getUsername());
            updatedUserDto.setName(updatedUser.getName());
            return updatedUserDto;

        }else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public UserDto login(PasswordDto passwordDto) {
        Optional<UserModel> userOptional = userRepository.findByEmail(passwordDto.getEmail());

        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            if (passwordEncoder.matches(passwordDto.getPassword(), user.getPassword())) {
                return UserDto.fromUser(user);
            } else {
                throw new IncorrectPassswordException("Password is incorrect");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }



    public Optional<UserModel> getUserByEmail(String email) {
        Optional<UserModel> userOptional = userRepository.findByEmail(email);

        if( userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }else{
            UserModel user = userOptional.get();
            user.setPassword(null);
            return Optional.of(user);
        }

    }
}
