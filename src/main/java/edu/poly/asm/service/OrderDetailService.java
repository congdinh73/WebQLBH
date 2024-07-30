package edu.poly.asm.service;

import edu.poly.asm.domain.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {

    Page<OrderDetail> findByOrderDetailId(Long orderDetailId, Pageable pageable);

    void deleteAllByIdInBatch(Iterable<Long> longs);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<OrderDetail> entities);

    void flush();

    OrderDetail getReferenceById(Long aLong);

    <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends OrderDetail> S saveAndFlush(S entity);

    List<OrderDetail> findAll();

    List<OrderDetail> findAllById(Iterable<Long> longs);

    <S extends OrderDetail> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAll();

    void delete(OrderDetail entity);

    void deleteAll(Iterable<? extends OrderDetail> entities);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteById(Long aLong);

    boolean existsById(Long aLong);

    Optional<OrderDetail> findById(Long aLong);

    <S extends OrderDetail> S save(S entity);

    List<OrderDetail> findAll(Sort sort);

    Page<OrderDetail> findAll(Pageable pageable);
}
