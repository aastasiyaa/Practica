package ru.example.survey.service;

import ru.example.survey.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll(int pageNumber, int pageSize, String direction, String sortByField);
    List<Order> findAllByOwnerId(long userId, int pageNumber, int pageSize, String direction, String sortByField);
    Order findById(long orderId);
    Order create(Order order);
    Order putUpdate(Order order, long orderId);
    void delete(long orderId);
}
