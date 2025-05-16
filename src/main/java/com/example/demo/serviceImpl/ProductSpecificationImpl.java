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
            List<ProductSpecification> specsToRemove = new ArrayList<>();
            List<ProductSpecification> specsToUpdate = new ArrayList<>();
            List<ProductSpecification> specsToAdd = new ArrayList<>();
            
            // Xác định spec nào cần cập nhật, thêm mới hoặc xóa
            for (ProductSpecification existingSpec : existingSpecs) {
                boolean found = false;
                for (ProductSpecification newSpec : productSpecifications) {
                    if (existingSpec.getId() > 0 && newSpec.getId() > 0 && existingSpec.getId() == newSpec.getId()) {
                        // Cập nhật specification hiện có
                        existingSpec.setSize(newSpec.getSize());
                        existingSpec.setColor(newSpec.getColor());
                        existingSpec.setCount(newSpec.getCount());
                        specsToUpdate.add(existingSpec);
                        found = true;
                        break;
                    } else if (existingSpec.getSize().equals(newSpec.getSize()) && 
                               existingSpec.getColor().equals(newSpec.getColor())) {
                        // Cập nhật specification hiện có (tìm theo thuộc tính)
                        existingSpec.setCount(newSpec.getCount());
                        specsToUpdate.add(existingSpec);
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    // Chỉ xóa specification không còn sử dụng và không có trong OrderDetail
                    boolean inUse = isSpecificationInUse(existingSpec);
                    if (!inUse) {
                        specsToRemove.add(existingSpec);
                    } else {
                        // Nếu đang được sử dụng, giữ lại nhưng đánh dấu là không còn hoạt động
                        existingSpec.setCount(0);
                        specsToUpdate.add(existingSpec);
                    }
                }
            }
            
            // Xác định spec nào cần thêm mới
            for (ProductSpecification newSpec : productSpecifications) {
                boolean isNew = true;
                
                // Kiểm tra nếu không phải là spec mới
                for (ProductSpecification spec : specsToUpdate) {
                    if ((newSpec.getId() > 0 && spec.getId() == newSpec.getId()) ||
                        (newSpec.getSize().equals(spec.getSize()) && newSpec.getColor().equals(spec.getColor()))) {
                        isNew = false;
                        break;
                    }
                }
                
                if (isNew) {
                    // Tạo và thêm specification mới
                    ProductSpecification spec = new ProductSpecification();
                    spec.setColor(newSpec.getColor());
                    spec.setSize(newSpec.getSize());
                    spec.setCount(newSpec.getCount());
                    spec.setProduct(product);
                    specsToAdd.add(spec);
                }
            }
            
            // Thực hiện cập nhật cơ sở dữ liệu
            List<ProductSpecification> result = new ArrayList<>();
            
            // Xóa các specification không còn sử dụng
            if (!specsToRemove.isEmpty()) {
                productSpecificationRepo.deleteAll(specsToRemove);
            }
            
            // Cập nhật các specification hiện có
            for (ProductSpecification spec : specsToUpdate) {
                result.add(productSpecificationRepo.save(spec));
            }
            
            // Thêm mới các specification
            for (ProductSpecification spec : specsToAdd) {
                result.add(productSpecificationRepo.save(spec));
            }
            
            return result;
        } catch (Exception e) {
            log.error("Lỗi khi cập nhật danh sách thông số kỹ thuật: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public boolean isSpecificationInUse(ProductSpecification productSpecification) {
        if (productSpecification == null || productSpecification.getId() == 0) {
            return false;
        }
        return productSpecificationRepo.countOrderDetailsBySpecificationId(productSpecification.getId()) > 0;
    }
}
