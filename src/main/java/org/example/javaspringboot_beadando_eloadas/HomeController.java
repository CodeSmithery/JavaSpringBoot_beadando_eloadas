package org.example.javaspringboot_beadando_eloadas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}