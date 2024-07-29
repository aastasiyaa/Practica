package ru.example.survey.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.example.survey.model.OrderType;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String comment;
    private OrderType orderType;
    private LocalDateTime dateOfCreation;
}
