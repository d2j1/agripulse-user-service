package com.app.agripulse_userservice.models;

import com.app.agripulse_userservice.models.BaseModel;
import com.app.agripulse_userservice.models.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends BaseModel implements UserDetails {

    private String name;
    private String countryCode;
    private String mobileNo;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return mobileNo;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Account is always non-expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Account is always non-locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // No credentials to expire
    }

    @Override
    public boolean isEnabled() {
        return true; // Enable account by default
    }
}
