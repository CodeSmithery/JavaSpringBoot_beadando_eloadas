package org.example.javaspringboot_beadando_eloadas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import soapclient.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class SoapController {

    @ModelAttribute
    public void commonModel(Model model) {
        model.addAttribute("formFilled", false);
        model.addAttribute("param", new MessagePrice());
    }

    @RequestMapping("/soap")
    public String soap(){
        return "soap";
    }


    @GetMapping("/soap/mnb_request")
    public String input(Model model){
        model.addAttribute("param", new MessagePrice());
        return "soap";
    }
    @PostMapping("/soap/mnb_request")
    public String output(@ModelAttribute MessagePrice messagePrice, Model model) throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage, JsonProcessingException {
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        MNBArfolyamServiceSoap service = impl.getCustomBindingMNBArfolyamServiceSoap();

        String xml = service.getExchangeRates(messagePrice.getStartDate().toString(),messagePrice.getEndDate().toString(),messagePrice.getCurrency());
        String currency = messagePrice.getCurrency();
//        System.out.println(xml);

        XmlMapper mapper = new XmlMapper();
        ExchangeRates ratesByDay = mapper.readValue(xml, ExchangeRates.class);

//        model.addAttribute("sendOut", xml);
        model.addAttribute("outObject", ratesByDay);
        model.addAttribute("currency", currency);
        model.addAttribute("formFilled",true);

        return "soap";
    }
    public static class MessagePrice {
        private String currency;
        private LocalDate startDate;
        private LocalDate endDate;

        public String getCurrency() {
            return currency;
        }
        public void setCurrency(String currency) { this.currency = currency; }

        public LocalDate getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = LocalDate.parse(startDate); }

        public LocalDate getEndDate() {
            return endDate;
        }
        public void setEndDate(String endDate) { this.endDate = LocalDate.parse(endDate); }
    }

    public static class ExchangeDay {
        @JacksonXmlProperty(isAttribute = true, localName = "date")
        private String date;
        public String getDate() { return date; }

        @JacksonXmlProperty(localName = "Rate")
        private Rate rate;
        public Rate getRate() { return rate; }
    }

    public static class Rate {
        @JacksonXmlProperty(isAttribute = true, localName = "unit")
        private int unit;
        public int getUnit() { return unit; }

        @JacksonXmlProperty(isAttribute = true, localName = "curr")
        private String currency;
        public String getCurrency() { return currency; }

        @JacksonXmlText
        private String value;
        public String getValue() { return value; }
    }

    @JacksonXmlRootElement(localName = "MNBExchangeRates")
    public static class ExchangeRates {
        @JacksonXmlElementWrapper(useWrapping = false)

        @JacksonXmlProperty(localName = "Day")
        private List<ExchangeDay> days;
        public List<ExchangeDay> getDays() { return days; }
    }
}
