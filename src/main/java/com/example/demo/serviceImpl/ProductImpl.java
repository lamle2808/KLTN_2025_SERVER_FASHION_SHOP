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
        int count = 0;
        for (ProductSpecification productSpecification : product.getSpecifications()) {
            count = count + productSpecification.getCount();
        }
        product.setQuantity(count);
        
        if (product.getId() == null) {
            Date currentDate = new Date();
            product.setImportDate(currentDate);
            product.setId(randomId());
            
            List<ProductSpecification> productSpecifications = new ArrayList<>();
            for (ProductSpecification productSpecification : product.getSpecifications()) {
                productSpecification.setProduct(product);
                productSpecifications.add(productSpecification);
            }
            product.setSpecifications(productSpecifications);
            
            // Chỉ lưu một lần
            return productRepo.save(product);
        }
        
        // Phần code xử lý khi sản phẩm đã tồn tại
        int i = 0;
        List<ProductSpecification> productSpecifications = new ArrayList<>();
        Product productUpdate = productRepo.findProductById(product.getId());
        for (ProductSpecification productSpecification : product.getSpecifications()) {
            if (productSpecification.getColor().equals(productUpdate.getSpecifications().get(i).getColor()) && productSpecification.getSize().equals(productUpdate.getSpecifications().get(i).getSize())) {
                productUpdate.getSpecifications().get(i).setCount(productUpdate.getSpecifications().get(i).getCount() + productSpecification.getCount());
                productSpecifications.add(productUpdate.getSpecifications().get(i));
            } else{
                productSpecifications.add(productSpecification);
                productSpecifications.add(productUpdate.getSpecifications().get(i));
            }
            i++;
            if (i >= productUpdate.getSpecifications().size()) {
                break;
            }
        }
        productUpdate.setSpecifications(productSpecifications);
        productUpdate.setQuantity(count);
        productUpdate.setImportDate(product.getImportDate());
        productUpdate.setProductName(product.getProductName());
        productUpdate.setBrand(product.getBrand());
        productUpdate.setDescription(product.getDescription());
        productUpdate.setPrice(product.getPrice());
        productUpdate.setImageProducts(product.getImageProducts());
        return productRepo.save(productUpdate);
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
