package example.test;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/cookie")
public class CookieController {

    @GetMapping("/add")
    public String add(@RequestParam(name = "data") String data,
                      @CookieValue(value = "dataList", required = false) String saved,
                      HttpServletResponse response) {
        try {
            String value;
            if (saved == null) {
                value = data;
            } else {
                value = saved + "-" + data;
            }
            ResponseCookie cookie = ResponseCookie.from("dataList", value)
                    .path("/")
                    .maxAge(Duration.ofDays(1))
                    .build();
            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return "쿠키저장성공";
        } catch (Exception e) {
            return "쿠키저장실패";
        }
    }

    @GetMapping("/all")
    public String[] all(@CookieValue(value = "dataList", required = false) String saved) {
        if (saved == null) {
            return new String[0];
        }
        return saved.split("-");
    }
}