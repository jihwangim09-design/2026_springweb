package example.totalpractice1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.totalpractice1.dto.CategoryDto;

@RestController 
@RequestMapping ("/api/categories")
public class CategoryController {
    @Autowired private CategoryService categoryService;

    @PostMapping("")
    public CategoryDto save(@RequestBody CategoryDto categoryDto){
        return categoryService.save(categoryDto);
    }

    @GetMapping("")
    public List<CategoryDto> findAll(){
        return categoryService.findAll();
    }

    @DeleteMapping("")
    public boolean delete(@RequestParam(name="cno") Integer cno){
        return categoryService.delete(cno);
    }





}
