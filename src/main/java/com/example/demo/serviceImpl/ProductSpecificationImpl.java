package com.example.demo.serviceImpl;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;
import com.example.demo.repository.ProductSpecificationRepo;
import com.example.demo.service.ProductSpecificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductSpecificationImpl implements ProductSpecificationService {
    private final ProductSpecificationRepo productSpecificationRepo;
    
    @Override
    public ProductSpecification saveOrUpdate(ProductSpecification productSpecification) {
        return productSpecificationRepo.save(productSpecification);
    }

    @Override
    public ProductSpecification getById(int id) {
        return productSpecificationRepo.findProductSpecificationById(id);
    }

    @Override
    public void delete(ProductSpecification productSpecification) {
        productSpecificationRepo.delete(productSpecification);
    }

    @Override
    public List<ProductSpecification> getByProduct(Product product) {
        return productSpecificationRepo.findByProduct(product);
    }

    @Override
    public void deleteAllByProduct(Product product) {
        productSpecificationRepo.deleteAllByProduct(product);
    }

    @Override
    public List<ProductSpecification> updateList(List<ProductSpecification> productSpecifications, Product product) {
        try {
            // Lấy danh sách thông số hiện tại của sản phẩm
            List<ProductSpecification> existingSpecs = productSpecificationRepo.findByProduct(product);
            
            // Xóa các thông số hiện tại
            if (existingSpecs != null && !existingSpecs.isEmpty()) {
                productSpecificationRepo.deleteAll(existingSpecs);
            }
            
            // Lưu các thông số mới
            List<ProductSpecification> savedSpecs = new ArrayList<>();
            for (ProductSpecification spec : productSpecifications) {
                spec.setProduct(product);
                savedSpecs.add(productSpecificationRepo.save(spec));
            }
            
            return savedSpecs;
        } catch (Exception e) {
            log.error("Lỗi khi cập nhật danh sách thông số kỹ thuật: " + e.getMessage(), e);
            throw e;
        }
    }
}
