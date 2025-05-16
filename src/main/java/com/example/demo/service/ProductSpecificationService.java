package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;

import java.util.List;

public interface ProductSpecificationService {
    ProductSpecification saveOrUpdate(ProductSpecification productSpecification);
    ProductSpecification getById(int id);
    void delete(ProductSpecification productSpecification);
    List<ProductSpecification> updateList(List<ProductSpecification> productSpecifications, Product product);
    List<ProductSpecification> getByProduct(Product product);
    void deleteAllByProduct(Product product);

    /**
     * Kiểm tra xem ProductSpecification có đang được sử dụng trong OrderDetail không
     * @param productSpecification ProductSpecification cần kiểm tra
     * @return true nếu đang được sử dụng, false nếu không
     */
    boolean isSpecificationInUse(ProductSpecification productSpecification);
}
