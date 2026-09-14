package example.totalpractice1.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.day06.CategoryEntity;
import example.totalpractice1.dto.CategoryDto;
import example.totalpractice1.model.Repository.CategoryRepository;

@Service 
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;

    public CategoryDto save(CategoryDto categoryDto){
        CategoryEntity categoryEntity = categoryDto.toEntity();
        CategoryEntity savedCategoryEntity = categoryRepository.save(categoryEntity);
        if(savedCategoryEntity.getCno()>=1){return}
        return false;
    }

    public List<CategoryDto> findAll(){
        return categoryRepository.findAll().stream().map(CategoryDto::from).toList();
    }

    public boolean delete(Integer cno){
        Optional<CategoryEntity> optional = categoryRepository.findById(cno);
        if(optional.isPresent()){
            CategoryEntity categoryEntity = optional.get();
            categoryRepository.deleteById(cno);
            return true;
        }
        return false;
    }











}
