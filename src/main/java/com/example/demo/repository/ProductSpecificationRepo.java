package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductSpecificationRepo extends JpaRepository<ProductSpecification, Integer> {
    ProductSpecification findProductSpecificationById(int id);
    List<ProductSpecification> findByProduct(Product product);
    void deleteAllByProduct(Product product);
    
    @Query(value = "SELECT * FROM product_specifications WHERE product_id = :productId", nativeQuery = true)
    List<ProductSpecification> findByProductIdNative(@Param("productId") String productId);
}
