package com.example.demo.serviceImpl;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductSpecificationService;
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
    private final ProductSpecificationService productSpecificationService;

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
        if (product.getSpecifications() != null) {
            for (ProductSpecification productSpecification : product.getSpecifications()) {
                count += productSpecification.getCount();
            }
            product.setQuantity(count);
        }
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
        List<ProductSpecification> newSpecs = new ArrayList<>();
        
        // Cập nhật hoặc giữ lại các specifications hiện có
        for (ProductSpecification newSpec : product.getSpecifications()) {
            boolean specExists = false;
            
            // Tìm specification hiện có dựa trên size và color
            for (ProductSpecification existingSpec : existingSpecs) {
                if (existingSpec.getSize().equals(newSpec.getSize()) && 
                    existingSpec.getColor().equals(newSpec.getColor())) {
                    
                    // Cập nhật thông tin của specification hiện có
                    existingSpec.setCount(newSpec.getCount());
                    newSpecs.add(existingSpec);
                    specExists = true;
                    break;
                }
            }
            
            // Nếu specification không tồn tại, tạo mới
            if (!specExists) {
                ProductSpecification spec = new ProductSpecification();
                spec.setColor(newSpec.getColor());
                spec.setSize(newSpec.getSize());
                spec.setCount(newSpec.getCount());
                spec.setProduct(existingProduct);
                newSpecs.add(spec);
                existingSpecs.add(spec);
            }
        }
        
        // Đánh dấu không sử dụng cho các specification không còn trong danh sách mới
        for (ProductSpecification existingSpec : existingSpecs) {
            boolean stillInUse = false;
            
            for (ProductSpecification newSpec : newSpecs) {
                if (existingSpec.getId() == newSpec.getId() || 
                    (existingSpec.getSize().equals(newSpec.getSize()) && 
                     existingSpec.getColor().equals(newSpec.getColor()))) {
                    stillInUse = true;
                    break;
                }
            }
            
            // Đánh dấu specification không còn sử dụng
            if (!stillInUse) {
                existingSpec.setCount(0);
            }
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

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepo.findProductById(product.getId());
        if (existingProduct == null) {
            throw new RuntimeException("Không tìm thấy sản phẩm với ID: " + product.getId());
        }
        
        existingProduct.setProductName(product.getProductName());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImportDate(product.getImportDate());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setImageProducts(product.getImageProducts());
        
        // Cập nhật specifications
        if (product.getSpecifications() != null) {
            // Tính tổng số lượng từ specifications
            int totalQuantity = 0;
            for (ProductSpecification spec : product.getSpecifications()) {
                totalQuantity += spec.getCount();
            }
            existingProduct.setQuantity(totalQuantity);
            
            List<ProductSpecification> existingSpecs = existingProduct.getSpecifications();
            List<ProductSpecification> newSpecs = new ArrayList<>();
            
            // Xử lý các specification mới
            for (ProductSpecification newSpec : product.getSpecifications()) {
                // Tìm specification tương ứng trong danh sách cũ (nếu có)
                ProductSpecification matchedSpec = null;
                
                if (newSpec.getId() > 0) {
                    // Tìm theo ID
                    for (ProductSpecification oldSpec : existingSpecs) {
                        if (oldSpec.getId() == newSpec.getId()) {
                            matchedSpec = oldSpec;
                            break;
                        }
                    }
                } else {
                    // Tìm theo size và color
                    for (ProductSpecification oldSpec : existingSpecs) {
                        if (oldSpec.getSize().equals(newSpec.getSize()) && 
                            oldSpec.getColor().equals(newSpec.getColor())) {
                            matchedSpec = oldSpec;
                            break;
                        }
                    }
                }
                
                if (matchedSpec != null) {
                    // Cập nhật specification hiện có
                    matchedSpec.setSize(newSpec.getSize());
                    matchedSpec.setColor(newSpec.getColor());
                    matchedSpec.setCount(newSpec.getCount());
                    newSpecs.add(matchedSpec);
                } else {
                    // Tạo mới specification
                    ProductSpecification spec = new ProductSpecification();
                    spec.setColor(newSpec.getColor());
                    spec.setSize(newSpec.getSize());
                    spec.setCount(newSpec.getCount());
                    spec.setProduct(existingProduct);
                    newSpecs.add(spec);
                }
            }
            
            // Kiểm tra và xóa các specification không còn sử dụng
            List<ProductSpecification> toRemove = new ArrayList<>();
            for (ProductSpecification oldSpec : existingSpecs) {
                boolean found = false;
                for (ProductSpecification newSpec : newSpecs) {
                    if (oldSpec.getId() > 0 && oldSpec.getId() == newSpec.getId()) {
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    // Kiểm tra spec có đang được sử dụng trong OrderDetail không
                    boolean inUse = productSpecificationService.isSpecificationInUse(oldSpec);
                    if (!inUse) {
                        toRemove.add(oldSpec);
                    } else {
                        // Nếu đang được sử dụng, giữ lại nhưng đánh dấu là không còn hoạt động (ví dụ: đặt count = 0)
                        oldSpec.setCount(0);
                        newSpecs.add(oldSpec);
                    }
                }
            }
            
            // Xóa an toàn các specification không còn sử dụng
            for (ProductSpecification spec : toRemove) {
                existingSpecs.remove(spec);
            }
            
            // Thêm các specification mới
            for (ProductSpecification newSpec : newSpecs) {
                if (newSpec.getId() == 0) {
                    existingSpecs.add(newSpec);
                }
            }
        }
        
        return productRepo.save(existingProduct);
    }

}
