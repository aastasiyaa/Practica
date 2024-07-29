package ru.example.survey.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.example.survey.model.User;
import ru.example.survey.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmail(email.toLowerCase());
        return user.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with username - " + email + " not found"));
    }
}
