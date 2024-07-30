package edu.poly.asm.service;

import edu.poly.asm.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface OrderService {


    Page<Order> findByOrderId(Long orderId, Pageable pageable);

    void deleteAllByIdInBatch(Iterable<Long> longs);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Order> entities);

    void flush();

    Order getReferenceById(Long aLong);

    <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Order> S saveAndFlush(S entity);

    List<Order> findAll();

    List<Order> findAllById(Iterable<Long> longs);

    <S extends Order> List<S> saveAll(Iterable<S> entities);

    long count();

    void delete(Order entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Order> entities);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteById(Long aLong);

    boolean existsById(Long aLong);

    Optional<Order> findById(Long aLong);

    <S extends Order> S save(S entity);

    List<Order> findAll(Sort sort);

    Page<Order> findAll(Pageable pageable);
}
