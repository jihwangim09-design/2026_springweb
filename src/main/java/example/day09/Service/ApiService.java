package example.day09.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.day09.model.Dto.ApiDto;
import example.day09.model.Entity.ApiEntity;
import example.day09.model.Repository.ApiRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ApiService {
    @Autowired ApiRepository apiRepository;

    public List<ApiDto> findAll(){
        List<ApiEntity> apiEntities = apiRepository.findAll();
        List<ApiDto> apiDtos = apiEntities.stream().map( (entity) -> {return ApiDto.from(entity);} ).toList();
        return apiDtos;
    }

    public boolean save( ApiDto apiDto ){

        ApiEntity apiEntity = apiDto.ToEntity();
        ApiEntity saved = apiRepository.save(apiEntity);
        if (saved.getId() >= 1 ) {
            return true;
        }
        return false;
    } 

}
