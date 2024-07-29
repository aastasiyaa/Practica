package ru.example.survey.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.example.survey.model.dto.RegistrationDto;
import ru.example.survey.service.UserService;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public String getPageRegistration(Model model){
        if(!model.containsAttribute("registrationDto")){
            model.addAttribute("registrationDto", new RegistrationDto());
        }
        return "registration";
    }

    @PostMapping
    public String registerNewUser(@Valid RegistrationDto registrationDto, BindingResult bindingResult, RedirectAttributes redirectAttrs){
        if(bindingResult.hasErrors()
                || !registrationDto.getPassword().equals(registrationDto.getRepeatPassword())
                || !userService.saveUser(registrationDto)){
            redirectAttrs.getFlashAttributes().clear();
            redirectAttrs.addFlashAttribute("registrationDto", registrationDto);
            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.registrationDto", bindingResult);
            return "redirect:/registration";
        }
        return "redirect:/login";
    }
}
