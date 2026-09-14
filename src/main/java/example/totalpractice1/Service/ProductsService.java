package example.totalpractice1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.totalpractice1.dto.ProductResponseDto;
import example.totalpractice1.model.Entity.ProductsEntity;

@Service 
public class ProductsService {
    @Autowired 
    private ProductsRepository productsRepository;

    public List<ProductResponseDto> productFindAll() {
        List<ProductsEntity> productsEntities = productsRepository.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        productsEntities.forEach((productsEntity)->{
            ProductResponseDto productResponseDto = ProductResponseDto.from(productsEntity);
            productsEntity.getCategoryEntity().forEach((name)->{
                CategoryDto 
            })
        });
    }
}