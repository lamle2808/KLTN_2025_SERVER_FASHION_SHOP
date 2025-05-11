package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;
import com.example.demo.repository.ProductSpecificationRepo;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductSpecificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/productSpecifications")
@Slf4j
public class ProductSpecificationController {
    private final ProductSpecificationService productSpecificationService;
    private final ProductService productService;
    private final ProductSpecificationRepo productSpecificationRepo;

    @PostMapping("/saveOrUpdate/{productId}")
    public ResponseEntity<?> saveOrUpdate(@RequestBody ProductSpecification productSpecification, @PathVariable("productId") String productId) {
        try {
            Product product = productService.getById(productId);
            if (product == null) {
                return ResponseEntity.badRequest().body(productId + " not found !!");
            }
            productSpecification.setProduct(product);
            ProductSpecification check = productSpecificationService.saveOrUpdate(productSpecification);
            if (check == null) {
                return ResponseEntity.badRequest().body("failed !!");
            }
            return ResponseEntity.ok().body(check);

        } catch (Exception exception) {
            log.error("Lỗi khi lưu thông số: " + exception.getMessage(), exception);
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSpecification(@PathVariable("id") int id) {
        try {
            ProductSpecification productSpecification = productSpecificationService.getById(id);
            if (productSpecification == null) {
                return ResponseEntity.ok().body("product specification id: " + id + " not found!!");
            }
            productSpecificationService.delete(productSpecification);
            return ResponseEntity.ok().body("success !!");
        } catch (Exception exception) {
            log.error("Lỗi khi xóa thông số: " + exception.getMessage(), exception);
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception.getMessage());
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        try {
            ProductSpecification productSpecification = productSpecificationService.getById(id);
            if (productSpecification == null) {
                return ResponseEntity.badRequest().body(id + " not found !!");
            }
            return ResponseEntity.ok().body(productSpecification);
        } catch (Exception exception) {
            log.error("Lỗi khi lấy thông số theo ID: " + exception.getMessage(), exception);
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception.getMessage());
        }
    }

    @PostMapping("/updateList/{idProduct}")
    public ResponseEntity<?> updateList(@PathVariable("idProduct") String idProduct, @RequestBody List<ProductSpecification> productSpecifications) {
        try {
            log.info("Request to update specifications for product ID: " + idProduct);
            Product product = productService.getById(idProduct);
            if (product == null) {
                return ResponseEntity.badRequest().body("Product not found with ID: " + idProduct);
            }

            List<ProductSpecification> productSpecificationList = productSpecificationService.updateList(productSpecifications, product);
            return ResponseEntity.ok().body(productSpecificationList);
        } catch (Exception exception) {
            log.error("Lỗi khi cập nhật danh sách thông số: " + exception.getMessage(), exception);
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception.getMessage());
        }
    }

    @DeleteMapping("/deleteAll/{productId}")
    public ResponseEntity<?> deleteAll(@PathVariable("productId") String productId) {
        try {
            log.info("Request to delete all specifications for product ID: " + productId);
            Product product = productService.getById(productId);
            if (product == null) {
                return ResponseEntity.badRequest().body("Product not found with ID: " + productId);
            }

            List<ProductSpecification> specs = productSpecificationService.getByProduct(product);
            if (specs != null && !specs.isEmpty()) {
                productSpecificationRepo.deleteAll(specs);
                log.info("Deleted " + specs.size() + " specifications for product ID: " + productId);
            } else {
                log.info("No specifications found to delete for product ID: " + productId);
            }
            
            return ResponseEntity.ok().body("All specifications deleted successfully");
        } catch (Exception exception) {
            log.error("Lỗi khi xóa tất cả thông số: " + exception.getMessage(), exception);
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception.getMessage());
        }
    }

    @GetMapping("/getUniqueSpecifications/{productId}")
    public ResponseEntity<?> getUniqueSpecifications(@PathVariable("productId") String productId) {
        try {
            Product product = productService.getById(productId);
            if (product == null) {
                return ResponseEntity.badRequest().body("Product not found with ID: " + productId);
            }

            List<ProductSpecification> specs = productSpecificationService.getByProduct(product);
            if (specs == null || specs.isEmpty()) {
                return ResponseEntity.ok().body(specs);
            }

            // Chỉ lấy các thông số duy nhất dựa trên tên
            Set<String> uniqueNames = new HashSet<>();
            List<ProductSpecification> uniqueSpecs = specs.stream()
                .filter(spec -> uniqueNames.add(spec.getSpecificationName()))
                .collect(Collectors.toList());

            return ResponseEntity.ok().body(uniqueSpecs);
        } catch (Exception exception) {
            log.error("Lỗi khi lấy thông số duy nhất: " + exception.getMessage(), exception);
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception.getMessage());
        }
    }
    
    // Thêm endpoint mới để lấy thông số kỹ thuật theo product_id trực tiếp
    @GetMapping("/getByProductId/{productId}")
    public ResponseEntity<?> getByProductId(@PathVariable("productId") String productId) {
        try {
            log.info("Fetching specifications for product ID: " + productId);
            Product product = productService.getById(productId);
            if (product == null) {
                log.warn("Product not found with ID: " + productId);
                return ResponseEntity.badRequest().body("Product not found with ID: " + productId);
            }
            
            // Lấy specs từ relationship trong entity
            List<ProductSpecification> specsFromEntity = product.getSpecifications();
            log.info("Found " + (specsFromEntity != null ? specsFromEntity.size() : 0) + " specs from entity relationship");
            
            // Lấy specs từ repository trực tiếp
            List<ProductSpecification> specsFromRepo = productSpecificationService.getByProduct(product);
            log.info("Found " + (specsFromRepo != null ? specsFromRepo.size() : 0) + " specs from repository");
            
            // Trả về kết quả từ repository vì nó truy vấn trực tiếp
            return ResponseEntity.ok().body(specsFromRepo);
        } catch (Exception exception) {
            log.error("Lỗi khi lấy thông số theo product_id: " + exception.getMessage(), exception);
            return ResponseEntity.badRequest().body("Error fetching specifications: " + exception.getMessage());
        }
    }
    
    // Endpoint sửa lỗi cho native SQL query
    @GetMapping("/getByProductIdNative/{productId}")
    public ResponseEntity<?> getByProductIdNative(@PathVariable("productId") String productId) {
        try {
            log.info("Fetching specifications with native query for product ID: " + productId);
            
            // Sử dụng native query để lấy trực tiếp từ database
            List<ProductSpecification> specs = productSpecificationRepo.findByProductIdNative(productId);
            
            log.info("Found " + specs.size() + " specifications with native query");
            
            return ResponseEntity.ok().body(specs);
        } catch (Exception exception) {
            log.error("Lỗi khi lấy thông số theo native query: " + exception.getMessage(), exception);
            return ResponseEntity.badRequest().body("Error with native query: " + exception.getMessage());
        }
    }
    
    // Endpoint cho debug các thông số sản phẩm đang có trong database
    @GetMapping("/debug/allSpecifications")
    public ResponseEntity<?> getAllSpecifications() {
        try {
            List<ProductSpecification> allSpecs = productSpecificationRepo.findAll();
            log.info("Total specifications in database: " + allSpecs.size());
            
            // Lấy 10 spec đầu tiên để kiểm tra
            List<ProductSpecification> sampleSpecs = allSpecs.size() > 10 ? allSpecs.subList(0, 10) : allSpecs;
            
            return ResponseEntity.ok().body(sampleSpecs);
        } catch (Exception exception) {
            log.error("Lỗi khi debug tất cả thông số: " + exception.getMessage(), exception);
            return ResponseEntity.badRequest().body("Error debugging specifications: " + exception.getMessage());
        }
    }
}
