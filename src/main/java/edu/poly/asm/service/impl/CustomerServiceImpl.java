package edu.poly.asm.service.impl;

import edu.poly.asm.domain.Customer;
import edu.poly.asm.repository.CustomerRepository;
import edu.poly.asm.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Customer> findByNameContaining(String name, Pageable pageable) {
        return customerRepository.findByNameContaining(name, pageable);
    }

    @Override
    public List<Customer> findByNameContaining(String name) {
        return customerRepository.findByNameContaining(name);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        customerRepository.deleteAllByIdInBatch(longs);
    }


    @Override
    public void deleteAllInBatch() {
        customerRepository.deleteAllInBatch();
    }

    @Override
    public void deleteAllInBatch(Iterable<Customer> entities) {
        customerRepository.deleteAllInBatch(entities);
    }

    @Override
    public void flush() {
        customerRepository.flush();
    }

    @Override
    public Customer getReferenceById(Long aLong) {
        return customerRepository.getReferenceById(aLong);
    }

    @Override
    public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
        return customerRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends Customer> S saveAndFlush(S entity) {
        return customerRepository.saveAndFlush(entity);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
        return customerRepository.saveAll(entities);
    }

    @Override
    public List<Customer> findAllById(Iterable<Long> longs) {
        return customerRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

    @Override
    public void delete(Customer entity) {
        customerRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {
        customerRepository.deleteAll(entities);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        customerRepository.deleteAllById(longs);
    }

    @Override
    public void deleteById(Long aLong) {
        customerRepository.deleteById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return customerRepository.existsById(aLong);
    }

    @Override
    public Optional<Customer> findById(Long aLong) {
        return customerRepository.findById(aLong);
    }

    @Override
    public <S extends Customer> S save(S entity) {
        return customerRepository.save(entity);
    }

    @Override
    public List<Customer> findAll(Sort sort) {
        return customerRepository.findAll(sort);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
