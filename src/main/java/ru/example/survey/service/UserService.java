package ru.example.survey.service;

import ru.example.survey.model.User;
import ru.example.survey.model.dto.RegistrationDto;

public interface UserService {
    boolean saveUser(RegistrationDto registrationDto);
    long getUserIdByEmail(String email);
    User getUserByUsername(String email);
}
