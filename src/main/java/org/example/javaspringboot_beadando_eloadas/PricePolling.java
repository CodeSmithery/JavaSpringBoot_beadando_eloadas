package org.example.javaspringboot_beadando_eloadas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import com.oanda.v20.primitives.Instrument;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.oanda.v20.account.AccountInstrumentsRequest;
import com.oanda.v20.account.AccountInstrumentsResponse;
import com.oanda.v20.primitives.InstrumentName;

@Service
public class PricePolling {
    private List<Instrument> instruments = new ArrayList<>();
    private List<ClientPrice> clientPrices = new ArrayList<>();

    public List<ClientPrice> getClientPrices() {
        return clientPrices;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    @Scheduled(initialDelay = 0, fixedRate = 30000)
    public void refresh() {
        System.out.println("Refreshing price...");
        instruments = getAllInstruments();
        clientPrices = getAllClientPrices(instruments.stream().map(Instrument::getName).toList());
    }

    public List<Instrument> getAllInstruments() {
        try {
            Context ctx = new ContextBuilder(Config.URL)
                    .setToken(Config.TOKEN)
                    .setApplication("PricePolling")
                    .build();

            AccountInstrumentsRequest acRequest = new AccountInstrumentsRequest(Config.ACCOUNTID);

            AccountInstrumentsResponse acResponse = ctx.account.instruments(acRequest);

            return acResponse.getInstruments();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<ClientPrice> getAllClientPrices(List<InstrumentName> instrumentNames) {
        try {
            Context ctx = new ContextBuilder(Config.URL)
                    .setToken(Config.TOKEN)
                    .setApplication("PricePolling")
                    .build();
            PricingGetRequest request = new PricingGetRequest(Config.ACCOUNTID, instrumentNames);
            PricingGetResponse response = ctx.pricing.get(request);

            return response.getPrices();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}