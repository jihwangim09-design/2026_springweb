package example.test;

import java.util.ArrayList;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/redis")
@RequiredArgsConstructor
public class RedisController {

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/add")
    public String add(@RequestParam(name = "data") String data) {
        try {
            String value = stringRedisTemplate.opsForValue().get("data:list");
            ArrayList<String> list;
            if (value == null) {
                list = new ArrayList<>();
            } else {
                list = objectMapper.readValue(value, ArrayList.class);
            }
            list.add(data);
            String str = objectMapper.writeValueAsString(list);
            stringRedisTemplate.opsForValue().set("data:list", str);
            return "레디스저장성공";
        } catch (Exception e) {
            return "레디스저장실패";
        }
    }

    @GetMapping("/all")
    public ArrayList<String> all() throws JsonProcessingException {
        String value = stringRedisTemplate.opsForValue().get("data:list");
        if (value == null) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(value, ArrayList.class);
    }
}