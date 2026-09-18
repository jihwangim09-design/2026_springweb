package example.day08;

import example.totalpractice1.AppStart;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@CrossOrigin (origins = "http://localhost:5173")
@RestController 
@RequiredArgsConstructor // final로 선언된 필드를 매개변수로 받는 생성자를 자동생성 
public class ApiController {
    private final ApiService apiService;

    // 1.
    @GetMapping ("/test1") 
    public Map<String,Object> test1(){
        return apiService.test1();
    }

    // 2
    @GetMapping ("/test2")
    public Map<String,Object> test2(){
        return apiService.test2();
    }
    // 3
    @GetMapping ("/test3")
    public List<Map<String,Object>> test3(){
        return apiService.test3();
    }
    // 4
    @GetMapping ("/test4")
    public Map<String,Object> test4(){
        return apiService.test4();

    }
}
