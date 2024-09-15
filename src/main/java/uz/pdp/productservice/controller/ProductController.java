package uz.pdp.productservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.productservice.dto.ProductDto;
import uz.pdp.productservice.entity.ProductEntity;
import uz.pdp.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "product-controller", description = "Mahsulotlar uchun API")
public class ProductController {
    private final ProductService productService;


    @Operation(summary = "Mahsulot qo'shish")
    @PostMapping("/create")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductDto product, HttpServletRequest request) {
        return ResponseEntity.ok(productService.addProduct(product, request));
    }

    @Operation(summary = "Barcha mahsulotlarni ko'rish")
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @Operation(summary = "Id orqali mahsulotni olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Operation(summary = "Id orqali o'chirish")
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Operation(summary = "Id orqali mahsulotni yangilash")
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto product) {
        productService.updateProduct(product);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
