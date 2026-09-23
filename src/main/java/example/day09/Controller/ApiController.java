package example.day09.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.day09.Service.ApiService;
import example.day09.model.Dto.ApiDto;
@CrossOrigin ("http://localhost:5173/")
@RestController 
@RequestMapping ("/api")
public class ApiController {
    @Autowired ApiService apiService;

    @GetMapping ("")
    public List<ApiDto> findAll(){
        return apiService.findAll();
    }

    @PostMapping ("")
    public boolean save( @RequestBody ApiDto apiDto ){
        return apiService.save(apiDto);
    }

}
