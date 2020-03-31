package cn.dsc.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingshichen
 */
@RestController
@SpringBootApplication
public class FastDockerApplication {

    @GetMapping("/test")
    public String test() {
        return "docker run success";
    }

    public static void main(String[] args) {
        SpringApplication.run(FastDockerApplication.class, args);
    }
}
