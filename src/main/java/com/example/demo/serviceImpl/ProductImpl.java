package com.example.demo.serviceImpl;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getByCategory(Category category) {
        return productRepo.findProductByCategory(category);
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getByName(String name) {
        return productRepo.findProductByProductNameContaining(name);
    }

    @Override
    public Product getById(String id) {
        return productRepo.findProductById(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        // Tính tổng số lượng
        int count = 0;
        for (ProductSpecification productSpecification : product.getSpecifications()) {
            count += productSpecification.getCount();
        }
        product.setQuantity(count);
        
        // Trường hợp thêm mới sản phẩm
        if (product.getId() == null) {
            Date currentDate = new Date();
            product.setImportDate(currentDate);
            product.setId(randomId());
            
            // Thiết lập mối quan hệ giữa sản phẩm và thông số kỹ thuật
            for (ProductSpecification spec : product.getSpecifications()) {
                spec.setProduct(product);
            }
            
            return productRepo.save(product);
        }
        
        // Trường hợp cập nhật sản phẩm
        Product existingProduct = productRepo.findProductById(product.getId());
        if (existingProduct == null) {
            throw new RuntimeException("Không tìm thấy sản phẩm với ID: " + product.getId());
        }
        
        // Cập nhật thông tin cơ bản
        existingProduct.setQuantity(count);
        existingProduct.setProductName(product.getProductName());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImportDate(product.getImportDate());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setImageProducts(product.getImageProducts());
        
        // Xử lý specifications với cách an toàn hơn
        List<ProductSpecification> existingSpecs = existingProduct.getSpecifications();
        
        // Xóa tất cả specs hiện tại
        existingSpecs.clear();
        productRepo.save(existingProduct);  // Lưu trước để xóa orphans
        
        // Thêm specs mới
        for (ProductSpecification newSpec : product.getSpecifications()) {
            ProductSpecification spec = new ProductSpecification();
            spec.setColor(newSpec.getColor());
            spec.setSize(newSpec.getSize());
            spec.setCount(newSpec.getCount());
            spec.setProduct(existingProduct);
            existingSpecs.add(spec);
        }
        
        return productRepo.save(existingProduct);
    }

    @Override
    public String randomId() {
        Random random = new Random();
        String newId = "";
        boolean isUnique = false;
        while (!isUnique) {
            int number = random.nextInt(10000);
            newId = "SP" + String.format("%04d", number);
            List<Product> products = productRepo.findAll();
            if (products.isEmpty()) {
                isUnique = true;
            } else {
                String finalNewId = newId;
                boolean isDuplicate = products.stream().anyMatch(p -> p.getId().equals(finalNewId));
                if (!isDuplicate) {
                    isUnique = true;
                }
            }
        }
        return newId;
    }

    @Override
    public List<Product> listNeedUpdate() {
        List<Product> productListNeedUpdate = new ArrayList<>();
        for (Product product : productRepo.findAll()) {
            if (product.getImageProducts() == null || product.getProductName() == null || product.getBrand() == null
                    || product.getDescription() == null
                    || product.getPrice() == 0) {
                productListNeedUpdate.add(product);
            }
        }
        return productListNeedUpdate;
    }

    @Override
    public void delete(Product product) {
        productRepo.delete(product);
    }

}
