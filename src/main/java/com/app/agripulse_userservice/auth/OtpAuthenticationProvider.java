package com.app.agripulse_userservice.auth;

import com.app.agripulse_userservice.models.UserModel;
import com.app.agripulse_userservice.repository.UserRepository;
import com.app.agripulse_userservice.service.OtpService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private final OtpService otpService;
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String mobileNo = authentication.getName();
        String otp = (String) authentication.getCredentials();

        // check if user exists
        Optional<UserModel> user = userRepository.findByMobileNo(mobileNo);

        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }

        // validate
        if( !otpService.validateOtp(mobileNo, otp)){
            throw new RuntimeException("Invalid or Expired OTP");
        }


        return new OtpAuthenticationToken(user, otp, user.get().getRoles());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
