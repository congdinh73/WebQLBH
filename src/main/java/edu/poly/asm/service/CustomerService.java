package edu.poly.asm.service;

import edu.poly.asm.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface CustomerService {


    Page<Customer> findByNameContaining(String name, Pageable pageable);

    List<Customer> findByNameContaining(String name);

    void deleteAllByIdInBatch(Iterable<Long> longs);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Customer> entities);

    void flush();

    Customer getReferenceById(Long aLong);

    <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Customer> S saveAndFlush(S entity);

    List<Customer> findAll();

    <S extends Customer> List<S> saveAll(Iterable<S> entities);

    List<Customer> findAllById(Iterable<Long> longs);

    long count();

    void delete(Customer entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Customer> entities);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteById(Long aLong);

    boolean existsById(Long aLong);

    Optional<Customer> findById(Long aLong);

    <S extends Customer> S save(S entity);

    List<Customer> findAll(Sort sort);

    Page<Customer> findAll(Pageable pageable);
}
