package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSpecificationRepo extends JpaRepository<ProductSpecification, Integer> {
    ProductSpecification findProductSpecificationById(int id);
    List<ProductSpecification> findByProduct(Product product);
    void deleteAllByProduct(Product product);
    
    @Query(value = "SELECT * FROM product_specifications WHERE product_id = :productId", nativeQuery = true)
    List<ProductSpecification> findByProductIdNative(@Param("productId") String productId);

    /**
     * Kiểm tra xem ProductSpecification có tồn tại trong bất kỳ OrderDetail nào không
     * @param specId ID của ProductSpecification cần kiểm tra
     * @return Số lượng OrderDetail có chứa ProductSpecification này
     */
    @Query("SELECT COUNT(od) FROM OrderDetail od WHERE od.productSpecification.id = :specId")
    long countOrderDetailsBySpecificationId(@Param("specId") int specId);
}
