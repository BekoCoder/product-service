package uz.pdp.productservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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
    @Override
    public ProductEntity addProduct(ProductDto product) {
        ProductEntity productEntity = mapper.map(product, ProductEntity.class);
        if(isExistProduct(productEntity.getName())){
            throw new ProductNotFoundException("Bu mahsulot oldin qo'shilgan ");
        }
        return productRepository.save(productEntity);
    }

    @Override
    public void updateProduct(ProductDto product) {

    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public ProductDto getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new CustomException("Bunday mahsulot topilmadi"));
        if(productEntity != null){
            return mapper.map(productEntity, ProductDto.class);
        }
        throw new ProductNotFoundException("Bunday mahsulot topilmadi");
    }

    @Override
    public List<ProductDto> getProducts() {
        List<ProductEntity> all = productRepository.findAll();
        if(all.isEmpty()){
            throw new ProductNotFoundException("Bunday mahsulot topilmadi");
        }
       List<ProductDto> product=new ArrayList<>();
        for(ProductEntity productEntity : all){
            product.add(mapper.map(productEntity, ProductDto.class));
        }
        return product;
    }

    private boolean isExistProduct(String productName) {
      return productRepository.findByName(productName).isPresent();
    }
}
