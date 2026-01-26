package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    Page<Product> findAllBy(Pageable pageable);

    boolean existsByCode(String code);

    Optional<Product> findByCode(String code);

    @Modifying
    @Query("""
        UPDATE Product p
        SET p.stock = p.stock + :quantity,
            p.updatedAt = :now,
            p.version = p.version + 1
        WHERE p.id = :id
    """)
    void resupplyStock(@Param("id") UUID id,
                       @Param("quantity") Integer quantity,
                       @Param("now") LocalDateTime now);

    @Modifying
    @Query("""
        UPDATE Product p
        SET p.stock = p.stock - :quantity,
            p.updatedAt = :now,
            p.version = p.version + 1
        WHERE p.id = :id
    """)
    void checkoutStock(@Param("id") UUID id,
                       @Param("quantity") Integer quantity,
                       @Param("now") LocalDateTime now);
}
