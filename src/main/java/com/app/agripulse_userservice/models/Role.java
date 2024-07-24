package com.app.agripulse_userservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;



@Getter
@Setter
@Entity
public class Role extends BaseModel implements GrantedAuthority {


    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    // Getters and setters
}
