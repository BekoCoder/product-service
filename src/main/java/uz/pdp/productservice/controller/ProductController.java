package uz.pdp.productservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.productservice.dto.ProductDto;
import uz.pdp.productservice.entity.ProductEntity;
import uz.pdp.productservice.service.ProductService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "product-controller", description = "Mahsulotlar uchun API")
public class ProductController {
    private final ProductService productService;


    @Operation(summary = "Mahsulot qo'shish")
    @PostMapping("/create")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductDto product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

}
