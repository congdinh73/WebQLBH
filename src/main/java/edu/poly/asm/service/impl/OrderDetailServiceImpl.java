package edu.poly.asm.service.impl;

import edu.poly.asm.domain.OrderDetail;
import edu.poly.asm.repository.OrderDetailRepository;
import edu.poly.asm.service.OrderDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public Page<OrderDetail> findByOrderDetailId(Long orderDetailId, Pageable pageable) {
        return orderDetailRepository.findByOrderDetailId(orderDetailId, pageable);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        orderDetailRepository.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        orderDetailRepository.deleteAllInBatch();
    }

    @Override
    public void deleteAllInBatch(Iterable<OrderDetail> entities) {
        orderDetailRepository.deleteAllInBatch(entities);
    }

    @Override
    public void flush() {
        orderDetailRepository.flush();
    }

    @Override
    public OrderDetail getReferenceById(Long aLong) {
        return orderDetailRepository.getReferenceById(aLong);
    }

    @Override
    public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
        return orderDetailRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends OrderDetail> S saveAndFlush(S entity) {
        return orderDetailRepository.saveAndFlush(entity);
    }

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetail> findAllById(Iterable<Long> longs) {
        return orderDetailRepository.findAllById(longs);
    }

    @Override
    public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
        return orderDetailRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return orderDetailRepository.count();
    }

    @Override
    public void deleteAll() {
        orderDetailRepository.deleteAll();
    }

    @Override
    public void delete(OrderDetail entity) {
        orderDetailRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends OrderDetail> entities) {
        orderDetailRepository.deleteAll(entities);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        orderDetailRepository.deleteAllById(longs);
    }

    @Override
    public void deleteById(Long aLong) {
        orderDetailRepository.deleteById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return orderDetailRepository.existsById(aLong);
    }

    @Override
    public Optional<OrderDetail> findById(Long aLong) {
        return orderDetailRepository.findById(aLong);
    }

    @Override
    public <S extends OrderDetail> S save(S entity) {
        return orderDetailRepository.save(entity);
    }

    @Override
    public List<OrderDetail> findAll(Sort sort) {
        return orderDetailRepository.findAll(sort);
    }

    @Override
    public Page<OrderDetail> findAll(Pageable pageable) {
        return orderDetailRepository.findAll(pageable);
    }
}
