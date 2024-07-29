package ru.example.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.survey.model.Order;
import ru.example.survey.model.dto.RegistrationDto;
import ru.example.survey.service.OrderService;
import ru.example.survey.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class IndexController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public String getPageRegistration(Model model, Principal principal){
        long userId = userService.getUserIdByEmail(principal.getName());
        List<Order> orders = orderService.findAllByOwnerId(userId, 0, 10, "DESC", "dateOfCreation");
        model.addAttribute("orders", orders);
        return "index";
    }
}
