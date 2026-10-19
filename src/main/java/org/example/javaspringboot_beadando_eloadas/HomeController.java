package org.example.javaspringboot_beadando_eloadas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/soap")
    public String soap(){
        return "soap";
    }
    @RequestMapping("/forex_account")
    public String forex_account(){
        return "forex_account";
    }
    @RequestMapping("/forex_close")
    public String forex_close(){
        return "forex_close";
    }
    @RequestMapping("/forex_current")
    public String forex_current(){
        return "forex_current";
    }
    @RequestMapping("/forex_history")
    public String forex_history(){
        return "forex_history";
    }
    @RequestMapping("/forex_open")
    public String forex_open(){
        return "forex_open";
    }
    @RequestMapping("/forex_position")
    public String forex_position(){
        return "forex_position";
    }
}
