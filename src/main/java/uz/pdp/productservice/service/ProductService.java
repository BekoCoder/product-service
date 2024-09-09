package uz.pdp.productservice.service;

import uz.pdp.productservice.dto.ProductDto;
import uz.pdp.productservice.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity addProduct(ProductDto product);

    void updateProduct(ProductDto product);

    void deleteProduct(Long id);

    ProductDto getProductById(Long id);

    List<ProductDto> getProducts();

}
