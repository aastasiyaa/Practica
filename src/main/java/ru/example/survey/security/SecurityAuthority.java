package ru.example.survey.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.example.survey.model.Role;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private final Role role;

    @Override
    public String getAuthority() {
        return role.getRoleName();
    }
}