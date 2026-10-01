package example.test;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/redis")
@RequiredArgsConstructor
public class RedisController {

    private final RedisListService redisListService;

    @GetMapping("/add")
    public String add(@RequestParam(name = "data") String data) {
        try {
            redisListService.add(data);
            return "레디스저장성공";
        } catch (Exception e) {
            return "레디스저장실패";
        }
    }

    @GetMapping("/all")
    public ArrayList<String> all() throws JsonProcessingException {
        return redisListService.findAll();
    }
}