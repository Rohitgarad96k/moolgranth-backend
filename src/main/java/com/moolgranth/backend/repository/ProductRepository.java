package com.moolgranth.backend.repository;

import com.moolgranth.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring Boot writes all the SQL for us automatically!
}