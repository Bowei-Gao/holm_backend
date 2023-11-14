package com.example.holm_backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
// @CrossOrigin(origins = "http://localhost:8080")
public class ViewController {
    @RequestMapping("/")
    public String index() {
        return "forward:/index.html";
    }
}
