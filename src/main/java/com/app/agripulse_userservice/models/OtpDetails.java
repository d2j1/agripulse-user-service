package com.app.agripulse_userservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OtpDetails {
    private final String otp;
    private final long timestamp;
}
