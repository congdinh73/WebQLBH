package edu.poly.asm.service;

import edu.poly.asm.domain.Account;
import edu.poly.asm.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface AccountService {


    Page<Account> findByUsernameContaining(String name, Pageable pageable);

    Account login(String username, String password);

    void deleteAllByIdInBatch(Iterable<String> strings);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Account> entities);

    void flush();

    Account getReferenceById(String s);

    <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Account> S saveAndFlush(S entity);

    List<Account> findAll();

    List<Account> findAllById(Iterable<String> strings);

    <S extends Account> List<S> saveAll(Iterable<S> entities);

    long count();

    void delete(Account entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Account> entities);

    void deleteAllById(Iterable<? extends String> strings);

    void deleteById(String s);

    boolean existsById(String s);

    Optional<Account> findById(String s);

    <S extends Account> S save(S entity);

    List<Account> findAll(Sort sort);

    Page<Account> findAll(Pageable pageable);
}
