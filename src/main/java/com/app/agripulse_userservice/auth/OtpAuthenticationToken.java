package com.app.agripulse_userservice.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

public class OtpAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final Object credentials;

    public OtpAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities){
    super(authorities);
    this.principal = principal;
    this.credentials = credentials;
    setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
