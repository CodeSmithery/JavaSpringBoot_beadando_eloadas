package org.example.javaspringboot_beadando_eloadas;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import soapclient.*;

@Controller
public class SoapController {
    @RequestMapping("/soap")
    public String soap(){
        return "soap";
    }

    @GetMapping("/soap/kiir")
    @ResponseBody
    public String kiir() throws MNBArfolyamServiceSoapGetInfoStringFaultFaultMessage,
            MNBArfolyamServiceSoapGetCurrentExchangeRatesStringFaultFaultMessage,
            MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage {
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        MNBArfolyamServiceSoap service = impl.getCustomBindingMNBArfolyamServiceSoap();
        return service.getInfo()+"<br>"+service.getCurrentExchangeRates()+"<br>"+service.getExchangeRates("2022-08-14","2022-09-14","EUR");
    }
}
