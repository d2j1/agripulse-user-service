package com.app.agripulse_userservice.service;

import com.app.agripulse_userservice.models.OtpDetails;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    private final ConcurrentHashMap<String, OtpDetails> otpCache = new ConcurrentHashMap<>();
    private static final int OTP_LENGTH = 6;
    private static final long OTP_EXPIRY_TIME = TimeUnit.MINUTES.toMillis(5); // 5 mins expiry time converted in the milliseconds


    // otp generation
    public String generateOtp(String mobileNo){

        String otp = String.format("%0" + OTP_LENGTH + "d", new Random().nextInt((int) Math.pow(10, OTP_LENGTH)));
        otpCache.put(mobileNo, new OtpDetails(otp, System.currentTimeMillis()));

        // logic to send the otp on mobile number
        /*
        Twilio is the best service for this, gone throught it and can be integrated later in the code here
         */
        // sendOtpToMobile(String otp, String mobileNo);

        return otp;
    }

    public boolean validateOtp(String mobileNo, String otp){

        OtpDetails otpDetails = otpCache.get(mobileNo);

        if(otpDetails == null){
            return false;
        }

        // checking the expiry
        if(System.currentTimeMillis() - otpDetails.getTimestamp() > OTP_EXPIRY_TIME){
            otpCache.remove(mobileNo);
            return false;
        }

        return otpDetails.getOtp().equals(otp);
    }




}
