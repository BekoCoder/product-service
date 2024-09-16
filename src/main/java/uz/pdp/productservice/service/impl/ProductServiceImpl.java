package uz.pdp.productservice.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.productservice.dto.ProductDto;
import uz.pdp.productservice.entity.ProductEntity;
import uz.pdp.productservice.exception.CustomException;
import uz.pdp.productservice.exception.ProductNotFoundException;
import uz.pdp.productservice.repository.ProductRepository;
import uz.pdp.productservice.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final RestTemplate restTemplate;

    @Value("${application.apiUrl}")
    private String apiUrl;

    @Override
    public ProductEntity addProduct(ProductDto product, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                String.class
        );
        String role = response.getBody();
        if ("SUPER_ADMIN".equals(role)) {
            if (!isExistProduct(product.getName())) {
                return productRepository.save(mapper.map(product, ProductEntity.class));
            } else {
                throw new CustomException("Bu mahsulot oldin qo'shilgan");
            }
        }
        throw new CustomException("Mahsulot qo'shilmadi");


    }

    @Override
    public void updateProduct(ProductDto product) {
        ProductEntity productEntity = productRepository.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException("Bunday mahsulot topiladmi "));
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setQuantity(product.getQuantity());
        productRepository.save(productEntity);


    }

    @Override
    public void deleteProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Bu mahsulot topilmadi"));
        productRepository.delete(productEntity);

    }

    @Override
    public ProductDto getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new CustomException("Bunday mahsulot topilmadi"));
        if (productEntity != null) {
            return mapper.map(productEntity, ProductDto.class);
        }
        throw new ProductNotFoundException("Bunday mahsulot topilmadi");
    }

    @Override
    public List<ProductDto> getProducts(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                String.class
        );
        String role = response.getBody();
        List<ProductEntity> all = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        if ("SUPER_ADMIN".equals(role)) {
            for (ProductEntity productEntity : all) {
                productDtos.add(mapper.map(productEntity, ProductDto.class));
            }
            return productDtos;
        }
        throw new CustomException("Bu mahsulot topilmadi");

    }

    private boolean isExistProduct(String productName) {
        return productRepository.findByName(productName).isPresent();
    }
}
