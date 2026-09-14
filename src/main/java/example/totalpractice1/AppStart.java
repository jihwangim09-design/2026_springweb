    package example.totalpractice1;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

    @SpringBootApplication // 스프링부트 실행
    public class AppStart {
        public static void main(String[] args) {
            SpringApplication.run(AppStart.class);
        }   
    }
