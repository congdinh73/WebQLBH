package edu.poly.asm.service.impl;

import edu.poly.asm.domain.Order;

import edu.poly.asm.repository.OrderRepository;
import edu.poly.asm.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;


    @Override
    public Page<Order> findByOrderId(Long orderId, Pageable pageable) {
        return orderRepository.findByOrderId(orderId, pageable);
    }

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        orderRepository.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        orderRepository.deleteAllInBatch();
    }

    @Override
    public void deleteAllInBatch(Iterable<Order> entities) {
        orderRepository.deleteAllInBatch(entities);
    }

    @Override
    public void flush() {
        orderRepository.flush();
    }

    @Override
    public Order getReferenceById(Long aLong) {
        return orderRepository.getReferenceById(aLong);
    }

    @Override
    public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
        return orderRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends Order> S saveAndFlush(S entity) {
        return orderRepository.saveAndFlush(entity);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllById(Iterable<Long> longs) {
        return orderRepository.findAllById(longs);
    }

    @Override
    public <S extends Order> List<S> saveAll(Iterable<S> entities) {
        return orderRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public void delete(Order entity) {
        orderRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends Order> entities) {
        orderRepository.deleteAll(entities);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        orderRepository.deleteAllById(longs);
    }

    @Override
    public void deleteById(Long aLong) {
        orderRepository.deleteById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return orderRepository.existsById(aLong);
    }

    @Override
    public Optional<Order> findById(Long aLong) {
        return orderRepository.findById(aLong);
    }

    @Override
    public <S extends Order> S save(S entity) {
        return orderRepository.save(entity);
    }

    @Override
    public List<Order> findAll(Sort sort) {
        return orderRepository.findAll(sort);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
