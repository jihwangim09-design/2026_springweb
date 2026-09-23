package example.day09.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import example.day09.Service.ApiService;
import lombok.RequiredArgsConstructor;

@RestController 
public class ApiController {
    @Autowired ApiService apiService;
}
