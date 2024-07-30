package edu.poly.asm.service.impl;

import edu.poly.asm.domain.Category;
import edu.poly.asm.domain.Product;
import edu.poly.asm.repository.ProductRepository;
import edu.poly.asm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public Page<Product> findByNameContaining(String name, Pageable pageable) {
        return productRepository.findByNameContaining(name, pageable);
    }

    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {
        productRepository.deleteAllInBatch(entities);
    }

    @Override
    public void flush() {
        productRepository.flush();
    }

    @Override
    public Product getReferenceById(Long aLong) {
        return productRepository.getReferenceById(aLong);
    }

    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        return productRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        return productRepository.saveAndFlush(entity);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllById(Iterable<Long> longs) {
        return productRepository.findAllById(longs);
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return productRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        productRepository.deleteAll(entities);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        productRepository.deleteAllById(longs);
    }

    @Override
    public void deleteById(Long aLong) {
        productRepository.deleteById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return productRepository.existsById(aLong);
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return productRepository.findById(aLong);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void deleteAllInBatch() {
        productRepository.deleteAllInBatch();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        productRepository.deleteAllByIdInBatch(longs);
    }
}
