package example.day08;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

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
}
