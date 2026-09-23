package example.day09.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.day09.model.Repository.ApiRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ApiService {
    @Autowired ApiRepository apiRepository;


}
