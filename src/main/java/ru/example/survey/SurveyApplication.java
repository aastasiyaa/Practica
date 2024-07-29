package ru.example.survey;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.example.survey.model.Role;
import ru.example.survey.model.User;
import ru.example.survey.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@SpringBootApplication
public class SurveyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurveyApplication.class, args);
    }

    @Bean
    @Order(value = Integer.MIN_VALUE)
    public ApplicationRunner dataLoader(UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            userRepo.save(
                    User.builder()
                            .userId(1L)
                            .email("admin@mail.ru")
                            .password(encoder.encode("password"))
                            .roles(Collections.singleton(new Role(1, "ROLE_ADMIN")))
                            .build());
        };
    }

}
