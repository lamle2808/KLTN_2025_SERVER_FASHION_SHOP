package com.example.demo.serviceImpl;

import com.example.demo.entity.*;
import com.example.demo.repository.OrderDetailRepo;
import com.example.demo.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderDetailImpl implements OrderDetailService {
    private final OrderDetailRepo orderDetailRepo;
    private final CartItemService cartItemService;
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final LoHangService loHangService;
    private final SaleDetailService saleDetailService;

    @Override
    public OrderDetail saveOrUpdate(int idCart, OrderDetail orderDetail) {
        // Lấy thông tin giỏ hàng và sản phẩm
        ShoppingCart shoppingCart = shoppingCartService.getByIdCart(idCart);
        Product product = productService.getById(orderDetail.getProduct().getId());
        if (product == null) {
            return null;
        }
        
        // Xử lý lô hàng
        int sl = 0;
        LoHang loHangUpdate = null;
        for (LoHang loHang : loHangService.getByProduct(product)) {
            if (loHang.getStatus() == 1) {
                loHangUpdate = loHang;
                sl = loHang.getQuantity() - orderDetail.getQuantity();
                break;
            }
        }
        
        if (product.getQuantity() <= 0) {
            return null;
        }
        
        // Tìm và cập nhật ProductSpecification tương ứng
        ProductSpecification matchedSpec = null;
        for (ProductSpecification productSpec : product.getSpecifications()) {
            if (orderDetail.getProductSpecification().getId() == productSpec.getId()) {
                matchedSpec = productSpec;
                
                // Kiểm tra số lượng có đủ không
                if (productSpec.getCount() < orderDetail.getQuantity()) {
                    return null; // Không đủ số lượng
                }
                
                // Cập nhật số lượng của specification
                int newCount = productSpec.getCount() - orderDetail.getQuantity();
                productSpec.setCount(newCount);
                
                // Gán specification cho orderDetail
                orderDetail.setProductSpecification(productSpec);
                break;
            }
        }
        
        if (matchedSpec == null) {
            return null; // Không tìm thấy specification
        }
        
        // Kiểm tra tổng số lượng
        int slUpdate = product.getQuantity() - orderDetail.getQuantity();
        if (slUpdate < 0 || (loHangUpdate != null && sl < 0)) {
            return null;
        }
        
        // Cập nhật thông tin sale
        SaleDetail saleDetail = saleDetailService.getByProductAndStatus(1, product);
        if (saleDetail != null) {
            orderDetail.setSaleId(saleDetail.getSales().getId());
        }
        
        // Cập nhật lô hàng
        if (loHangUpdate != null) {
            loHangUpdate.setQuantity(sl);
            loHangService.saveOrUpdate(loHangUpdate);
            orderDetail.setLoHangId(loHangUpdate.getId());
        }
        
        // Cập nhật giá và thông tin đơn hàng
        orderDetail.setPrice(product.getPrice());
        
        // Cập nhật số lượng tổng thể của sản phẩm
        product.setQuantity(slUpdate);
        
        // Lưu sản phẩm đã cập nhật
        productService.saveOrUpdate(product);
        
        // Xóa sản phẩm khỏi giỏ hàng
        CartItem cartItem = cartItemService.getByProductAndCart(product, shoppingCart);
        cartItemService.remove(cartItem.getId());
        
        // Lưu chi tiết đơn hàng
        return orderDetailRepo.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getByOrder(Order order) {
        return orderDetailRepo.findOrderDetailByOrder(order);
    }

    @Override
    public OrderDetail createNow(OrderDetail orderDetail) {
        // Lấy thông tin sản phẩm
        Product product = productService.getById(orderDetail.getProduct().getId());
        if (product == null) {
            return null;
        }
        
        // Xử lý lô hàng
        int sl = 0;
        LoHang loHangUpdate = null;
        for (LoHang loHang : loHangService.getByProduct(product)) {
            if (loHang.getStatus() == 1) {
                loHangUpdate = loHang;
                sl = loHang.getQuantity() - orderDetail.getQuantity();
                break;
            }
        }
        
        if (product.getQuantity() <= 0) {
            return null;
        }
        
        // Tìm và cập nhật ProductSpecification tương ứng
        ProductSpecification matchedSpec = null;
        for (ProductSpecification productSpec : product.getSpecifications()) {
            if (orderDetail.getProductSpecification().getId() == productSpec.getId()) {
                matchedSpec = productSpec;
                
                // Kiểm tra số lượng có đủ không
                if (productSpec.getCount() < orderDetail.getQuantity()) {
                    return null; // Không đủ số lượng
                }
                
                // Cập nhật số lượng của specification
                int newCount = productSpec.getCount() - orderDetail.getQuantity();
                productSpec.setCount(newCount);
                
                // Gán specification cho orderDetail
                orderDetail.setProductSpecification(productSpec);
                break;
            }
        }
        
        if (matchedSpec == null) {
            return null; // Không tìm thấy specification
        }
        
        // Kiểm tra tổng số lượng
        int slUpdate = product.getQuantity() - orderDetail.getQuantity();
        if (slUpdate < 0 || (loHangUpdate != null && sl < 0)) {
            return null;
        }
        
        // Cập nhật thông tin sale
        SaleDetail saleDetail = saleDetailService.getByProductAndStatus(1, product);
        if (saleDetail != null) {
            orderDetail.setSaleId(saleDetail.getSales().getId());
        }
        
        // Cập nhật lô hàng
        if (loHangUpdate != null) {
            loHangUpdate.setQuantity(sl);
            loHangService.saveOrUpdate(loHangUpdate);
            orderDetail.setLoHangId(loHangUpdate.getId());
        }
        
        // Cập nhật giá và thông tin đơn hàng
        orderDetail.setPrice(product.getPrice());
        
        // Cập nhật số lượng tổng thể của sản phẩm
        product.setQuantity(slUpdate);
        
        // Lưu sản phẩm đã cập nhật
        productService.saveOrUpdate(product);
        
        // Lưu chi tiết đơn hàng
        return orderDetailRepo.save(orderDetail);
    }

    @Override
    public OrderDetail getById(int id) {
        return orderDetailRepo.findOrderDetailById(id);
    }

    @Override
    public void deleteOrderDetail(OrderDetail orderDetail) {
        orderDetailRepo.delete(orderDetail);
    }
}
