package com.app.agripulse_userservice.serviceimpl;

import com.app.agripulse_userservice.dtos.ConfirmationDto;
import com.app.agripulse_userservice.dtos.PasswordDto;
import com.app.agripulse_userservice.dtos.UserDto;
import com.app.agripulse_userservice.exceptions.UserNotFoundException;
import com.app.agripulse_userservice.models.User;
import com.app.agripulse_userservice.repository.UserRepository;
import com.app.agripulse_userservice.service.UserService;
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
    public UserDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());

            return userDto;
        }else{
            throw new UserNotFoundException("User not found");
        }
    }



    @Override
    public UserDto createUser(UserDto userDto) {

        User user = new User();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        User savedUser = userRepository.save(user);
        return UserDto.fromUser(savedUser);

    }

    @Override
    public ConfirmationDto updateUserPassword(PasswordDto passwordDto) {
       Optional<User>  userToUpdateOptional = userRepository.findById(passwordDto.getId());

       User userToUpdate = userToUpdateOptional.orElse(null);

       if( userToUpdate != null ) {
           userToUpdate.setPassword(passwordDto.getPassword());
           User updatedUser = userRepository.save(userToUpdate);
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
        List<User> users = userRepository.findAll();

        if( users.isEmpty() ){
            throw new UserNotFoundException("User not found");
        }

        List<UserDto> usersDtos = new ArrayList<>();

        for( User user : users ){
            usersDtos.add(UserDto.fromUser(user));
        }
        return usersDtos;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        Optional<User> fetchedUserOptional = userRepository.findById(id);

        if(fetchedUserOptional.isPresent()){
            User fetchedUser = fetchedUserOptional.get();

            fetchedUser.setName(userDto.getName());
            fetchedUser.setEmail(userDto.getEmail());
            fetchedUser.setUsername(userDto.getUsername());
            User updatedUser = userRepository.save(fetchedUser);

            UserDto updatedUserDto = new UserDto();
            updatedUserDto.setUsername(updatedUser.getUsername());
            updatedUserDto.setEmail(updatedUser.getEmail());
            updatedUserDto.setName(updatedUser.getName());
            return updatedUserDto;

        }else {
            throw new UserNotFoundException("User not found");
        }
    }
}
