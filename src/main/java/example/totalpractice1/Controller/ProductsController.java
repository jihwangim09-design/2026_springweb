package example.totalpractice1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.totalpractice1.Service.ProductsService;
import example.totalpractice1.dto.ProductDto;

@CrossOrigin (value = "http://localhost:5173")
@RestController 
@RequestMapping ("api/products")
public class ProductsController {
    private final ProductsService productsService;
    @Autowired private ProductsService produtsService;

    ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping ("")
    public List<ProductResponseDto> productFindAll() {
        return productsService.productFindAll(productDto);
    }

    @PostMapping ("")
    public boolean productSave(@RequestBody ProductDto productDto) {
        return productsService.productSave();
    }

    @PutMapping ("")
    public boolean productUpdate(@RequestBody ProductDto productDto) {
        return productsService.productUpdate(productDto);
    }

    @DeleteMapping ("")
    public boolean productDelete(
            @RequestParam (name = "bno") Integer bno) {
        return productsService.productDelete(bno);
    }
}
