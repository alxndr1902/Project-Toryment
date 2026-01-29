package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BranchRepo extends JpaRepository<Branch, UUID> {
    boolean existsByCode(String code);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("""
        SELECT b
        FROM Branch b
        WHERE (:branchName IS NULL OR
                b.name
                ILIKE CONCAT('%', :branchName, '%'))
        """)
    Page<Branch> findAllWithFilter(@Param("branchName") String branchName,
                                   Pageable pageable);
}
