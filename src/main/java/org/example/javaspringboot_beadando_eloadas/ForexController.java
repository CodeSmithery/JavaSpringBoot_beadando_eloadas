package org.example.javaspringboot_beadando_eloadas;

import com.v20.instrument.CandlestickGranularity;
import com.v20.primitives.InstrumentName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.v20.Context;
import com.v20.account.AccountSummary;

@Controller
public class ForexController {
    private final DataProviderService dataProvider;
    private final HistoryRequest historyRequest;


    public ForexController(HistoryRequest historyRequest, DataProviderService dataProvider) {
        this.historyRequest = historyRequest;
        this.dataProvider = dataProvider;
    }
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

    @ModelAttribute
    public void commonModel(Model model) {
        model.addAttribute("formFilled", false);
        model.addAttribute("candleRequest", new CandleRequest());
        model.addAttribute( "instruments", dataProvider.getInstruments());
        model.addAttribute("granualities", CandlestickGranularity.values());
    }

@RequestMapping("/forex_account")
    public String forex_account(Model model) {
    Context ctx = new Context(Config.URL,Config.TOKEN);

    try {
        AccountSummary summary = ctx.account.summary(Config.ACCOUNTID).getAccount();
        model.addAttribute("account",summary);
    }
    catch (Exception e) { e.printStackTrace(); }
        return "forex_account";
    }

    @RequestMapping("/forex_close")
    public String forex_close(){
        return "forex_close";
    }


    @RequestMapping("/forex_current")
    public String forex_current(Model model) {
        model.addAttribute( "prices", dataProvider.getClientPrices());
        return "forex_current";
    }

    @RequestMapping("/forex_history")
    public String forex() {return "forex_history";}

    @GetMapping("/forex_history/form")
    public String input (Model model){
        model.addAttribute("candleRequest", new CandleRequest());
        return "forex_history";
    }

    @PostMapping("/forex_history/form")
    public String output (@ModelAttribute CandleRequest candleRequest, Model model){
        model.addAttribute("candlesticks", historyRequest.getCandlesticks(candleRequest.instrumentName, candleRequest.granularity));
        model.addAttribute( "formFilled", true);
        return "forex_history";
    }


    public static class CandleRequest{
        private CandlestickGranularity granularity;
        private InstrumentName instrumentName;

        public CandlestickGranularity getGranularity() { return granularity; }
        public void setGranularity(CandlestickGranularity granularity) { this.granularity = granularity; }

        public InstrumentName getInstrumentName() { return instrumentName; }
        public void setInstrumentName(InstrumentName instrumentName) { this.instrumentName = instrumentName; }
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
