package ru.example.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.survey.model.Order;
import ru.example.survey.model.User;
import ru.example.survey.model.dto.OrderDto;
import ru.example.survey.service.OrderService;
import ru.example.survey.service.UserService;

import java.security.Principal;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public String getPageRegistration(Model model, Principal principal) {
        model.addAttribute("order", OrderDto.builder()
                .email(principal.getName())
                .build());
        return "survey";
    }

    @PostMapping
    public String save(OrderDto orderDto, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        orderService.create(Order.builder()
                .name(orderDto.getName())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .comment(orderDto.getComment())
                .orderType(orderDto.getOrderType())
                .dateOfCreation(LocalDateTime.now())
                .owner(user)
                .build());
        return "redirect:/";
    }
}
