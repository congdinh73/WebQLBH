package edu.poly.asm.repository;

import edu.poly.asm.domain.Category;
import edu.poly.asm.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContaining(String name);
    Page<Product> findByNameContaining(String name, Pageable pageable);
}
