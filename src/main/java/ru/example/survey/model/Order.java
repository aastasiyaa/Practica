package ru.example.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "srv_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String comment;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private LocalDateTime dateOfCreation;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;
}
