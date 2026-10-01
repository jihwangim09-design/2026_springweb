package example.test;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @GetMapping("/add")
    public String add(@RequestParam(name = "data") String data, HttpSession session) {
        try {
            Object obj = session.getAttribute("dataList");
            ArrayList<String> list;
            if (obj == null) {
                list = new ArrayList<>();
            } else {
                list = (ArrayList<String>) obj;
            }
            list.add(data);
            session.setAttribute("dataList", list);
            return "세션저장성공";
        } catch (Exception e) {
            return "세션저장실패";
        }
    }

    @GetMapping("/all")
    public ArrayList<String> all(HttpSession session) {
        Object obj = session.getAttribute("dataList");
        if (obj == null) {
            return new ArrayList<>();
        }
        return (ArrayList<String>) obj;
    }
}