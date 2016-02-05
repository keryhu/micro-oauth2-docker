package demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@RequestMapping("/hello")
    public String home() {
        return "Hello 这是config-server 的hello 页面，欢迎光临!";
    }
	

}
