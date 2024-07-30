package edu.poly.asm.service;

import edu.poly.asm.domain.Category;
import edu.poly.asm.domain.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ProductService {

    List<Product> findByNameContaining(String name);

    Page<Product> findByNameContaining(String name, Pageable pageable);

    void deleteAllInBatch(Iterable<Product> entities);

    void flush();

    Product getReferenceById(Long aLong);

    <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Product> S saveAndFlush(S entity);

    List<Product> findAll();

    List<Product> findAllById(Iterable<Long> longs);

    <S extends Product> List<S> saveAll(Iterable<S> entities);

    long count();

    void delete(Product entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Product> entities);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteById(Long aLong);

    boolean existsById(Long aLong);

    Optional<Product> findById(Long aLong);

    <S extends Product> S save(S entity);

    List<Product> findAll(Sort sort);

    Page<Product> findAll(Pageable pageable);

    void deleteAllInBatch();

    void deleteAllByIdInBatch(Iterable<Long> longs);
}
