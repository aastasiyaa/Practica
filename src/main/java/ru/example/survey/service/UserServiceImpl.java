package ru.example.survey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.example.survey.exception.UsernameNotFoundException;
import ru.example.survey.model.Role;
import ru.example.survey.model.User;
import ru.example.survey.model.dto.RegistrationDto;
import ru.example.survey.repository.UserRepository;
import ru.example.survey.security.SecurityUser;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean saveUser(RegistrationDto registrationDto){
        if(userRepository.findByEmail(registrationDto.getEmail().toLowerCase()).isPresent()){
            System.out.println("email is present");
            return false;
        }
        User user = new User();
        user.setEmail(registrationDto.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRoles(Collections.singleton(new Role(2, "ROLE_USER")));
        userRepository.save(user);
        return true;
    }

    @Override
    public long getUserIdByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with username - " + email + " not found"))
                .getUserId();
    }

    @Override
    public User getUserByUsername(String email) {
        return userRepository.findByEmail(email.toLowerCase()).stream()
                .findAny()
                .orElseThrow(() -> new org.springframework.security.core.userdetails.UsernameNotFoundException("User with username - " + email + " not found"));
    }
}
