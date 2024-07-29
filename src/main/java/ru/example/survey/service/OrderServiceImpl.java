package ru.example.survey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.survey.exception.OrderNotFoundException;
import ru.example.survey.model.Order;
import ru.example.survey.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll(int pageNumber, int pageSize, String direction, String sortByField) {
        Sort.Direction sortDirection = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortByField);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return orderRepository.findAll(pageRequest).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAllByOwnerId(long userId, int pageNumber, int pageSize, String direction, String sortByField) {
        Sort.Direction sortDirection = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortByField);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return orderRepository.findAllByOwnerUserId(userId, pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id - " + orderId + " not found."));
    }

    @Override
    @Transactional
    public Order create(Order order) {
        if(order.getDateOfCreation() == null){
            order.setDateOfCreation(LocalDateTime.now());
        }
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order putUpdate(Order order, long orderId) {
        orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order with id - " + orderId + " not found."));
        order.setId(orderId);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void delete(long orderId) {
        orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order with id - " + orderId + " not found."));
        orderRepository.deleteById(orderId);
    }

}
