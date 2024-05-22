package com.nexoscredibanco.Repository;

import com.nexoscredibanco.Entity.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
    Optional<AccountHolder> findByProductsProductId(Long productId);
}
