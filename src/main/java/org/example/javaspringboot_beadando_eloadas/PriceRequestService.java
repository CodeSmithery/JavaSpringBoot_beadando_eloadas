package org.example.javaspringboot_beadando_eloadas;

import java.util.ArrayList;
import java.util.List;

import com.v20.Context;
import com.v20.ContextBuilder;
import com.v20.pricing.ClientPrice;
import com.v20.pricing.PricingGetRequest;
import com.v20.pricing.PricingGetResponse;
import com.v20.primitives.Instrument;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.v20.account.AccountInstrumentsRequest;
import com.v20.account.AccountInstrumentsResponse;
import com.v20.primitives.InstrumentName;

@Service
public class PriceRequestService {
    private final Context ctx = ContextProviderService.getContext("ContextProviderService");
    public List<ClientPrice> getAllClientPrices(List<InstrumentName> instrumentNames) {
        try {
            PricingGetRequest request = new PricingGetRequest(Config.ACCOUNTID, instrumentNames);
            PricingGetResponse response = ctx.pricing.get(request);

            return response.getPrices();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}