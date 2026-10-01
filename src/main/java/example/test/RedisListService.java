package example.test;

import java.util.ArrayList;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisListService {

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void add(String data) throws JsonProcessingException {
        ArrayList<String> list = findAll();
        list.add(data);
        String value = objectMapper.writeValueAsString(list);
        stringRedisTemplate.opsForValue().set("data:list", value);
    }

    public ArrayList<String> findAll() throws JsonProcessingException {
        String value = stringRedisTemplate.opsForValue().get("data:list");
        if (value == null) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(value, ArrayList.class);
    }
}