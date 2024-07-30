package edu.poly.asm.repository;

import edu.poly.asm.domain.Account;
import edu.poly.asm.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Page<Account> findByUsernameContaining(String name , Pageable pageable);
}
