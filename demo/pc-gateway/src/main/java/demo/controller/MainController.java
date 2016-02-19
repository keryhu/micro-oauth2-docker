package demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
	
	@RequestMapping("/")
    public String home() {
        return "Hello ，this is pc-gateway's index page，!";
    }

	@RequestMapping("/hello")
    public String hello() {
        return "Hello this is pc-gateway'hello page!";
    }
	
	

	
	
}
