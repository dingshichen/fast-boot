package cn.dsc.docker.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingshichen
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "docker haha";
    }
}
