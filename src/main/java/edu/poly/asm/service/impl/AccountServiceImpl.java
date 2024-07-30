package edu.poly.asm.service.impl;

import edu.poly.asm.domain.Account;
import edu.poly.asm.repository.AccountRepository;
import edu.poly.asm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Page<Account> findByUsernameContaining(String name, Pageable pageable) {
        return accountRepository.findByUsernameContaining(name, pageable);
    }

    @Override
    public Account login(String username, String password) {
        Optional<Account> opt = accountRepository.findById(username);
        if (opt.isPresent() && bCryptPasswordEncoder.matches(password, opt.get().getPassword())) {
            opt.get().setPassword("");
            return opt.get();
        }
        return null;

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {
        accountRepository.deleteAllByIdInBatch(strings);
    }


    @Override
    public void deleteAllInBatch() {
        accountRepository.deleteAllInBatch();
    }

    @Override
    public void deleteAllInBatch(Iterable<Account> entities) {
        accountRepository.deleteAllInBatch(entities);
    }

    @Override
    public void flush() {
        accountRepository.flush();
    }

    @Override
    public Account getReferenceById(String s) {
        return accountRepository.getReferenceById(s);
    }

    @Override
    public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
        return accountRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends Account> S saveAndFlush(S entity) {
        return accountRepository.saveAndFlush(entity);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAllById(Iterable<String> strings) {
        return accountRepository.findAllById(strings);
    }

    @Override
    public <S extends Account> List<S> saveAll(Iterable<S> entities) {
        return accountRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return accountRepository.count();
    }

    @Override
    public void delete(Account entity) {
        accountRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        accountRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends Account> entities) {
        accountRepository.deleteAll(entities);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        accountRepository.deleteAllById(strings);
    }

    @Override
    public void deleteById(String s) {
        accountRepository.deleteById(s);
    }

    @Override
    public boolean existsById(String s) {
        return accountRepository.existsById(s);
    }

    @Override
    public Optional<Account> findById(String s) {
        return accountRepository.findById(s);
    }

    @Override
    public <S extends Account> S save(S entity) {
        Optional<Account> optExist = findById(entity.getUsername());
        if (optExist.isPresent()) {
            if (StringUtils.isEmpty(entity.getPassword())) {
                entity.setPassword(optExist.get().getPassword());
            } else {
                entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
            }
        } else {
            entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        }

        return accountRepository.save(entity);
    }

    @Override
    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }
}
