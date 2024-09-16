package uz.pdp.productservice.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.pdp.productservice.dto.ProductDto;
import uz.pdp.productservice.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity addProduct(ProductDto product, HttpServletRequest request);

    void updateProduct(ProductDto product);

    void deleteProductById(Long id);

    ProductDto getProductById(Long id);

    List<ProductDto> getProducts(HttpServletRequest request);

}
