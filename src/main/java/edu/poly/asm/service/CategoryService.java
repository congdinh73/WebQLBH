package edu.poly.asm.service;

import edu.poly.asm.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Category> entities);

    void flush();

    List<Category> findByNameContaining(String name);

    Page<Category> findByNameContaining(String name, Pageable pageable);

    Category getReferenceById(Long aLong);

    <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Category> S saveAndFlush(S entity);

    List<Category> findAll();

    List<Category> findAllById(Iterable<Long> longs);

    <S extends Category> List<S> saveAll(Iterable<S> entities);

    long count();

    void delete(Category entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Category> entities);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteById(Long aLong);

    boolean existsById(Long aLong);

    Optional<Category> findById(Long aLong);

    <S extends Category> S save(S entity);

    List<Category> findAll(Sort sort);

    Page<Category> findAll(Pageable pageable);
}
