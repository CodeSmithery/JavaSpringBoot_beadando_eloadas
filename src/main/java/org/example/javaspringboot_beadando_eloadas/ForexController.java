package org.example.javaspringboot_beadando_eloadas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.oanda.v20.Context;
import com.oanda.v20.account.AccountSummary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ForexController {
//    @GetMapping("/account_info")
//    @ResponseBody
//    public AccountSummary f1() {
//        Context ctx = new Context(Config.URL,Config.TOKEN);
//        try {
//            AccountSummary summary = ctx.account.summary(Config.ACCOUNTID).getAccount();
//            return summary;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

@RequestMapping("/forex_account")
    public String forex_account(Model model) {
    Context ctx = new Context(Config.URL,Config.TOKEN);

    try {
        AccountSummary summary = ctx.account.summary(Config.ACCOUNTID).getAccount();
        model.addAttribute("account",summary);
    }
    catch (Exception e) {
        e.printStackTrace();
    }

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
