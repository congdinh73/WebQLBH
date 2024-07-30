package edu.poly.asm.repository;

import edu.poly.asm.domain.Customer;
import edu.poly.asm.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContaining(String name);
    Page<Customer> findByNameContaining(String name, Pageable pageable);
}
