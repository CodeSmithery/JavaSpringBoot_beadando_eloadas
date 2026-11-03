package org.example.javaspringboot_beadando_eloadas;

import com.v20.Context;
import com.v20.ContextBuilder;
import com.v20.order.MarketOrderRequest;
import com.v20.order.OrderCreateRequest;
import com.v20.order.OrderCreateResponse;
import com.v20.primitives.DecimalNumber;
import com.v20.primitives.InstrumentName;
import org.springframework.stereotype.Service;

@Service
public class OpenNewPositionService {
    private final Context ctx = ContextProviderService.getContext("OpenNewPositionService");

    public void openPosition(InstrumentName instrumentName, DecimalNumber units) {
        try{
            OrderCreateRequest orderCreateRequest = new OrderCreateRequest(Config.ACCOUNTID);
            MarketOrderRequest marketOrderRequest = new MarketOrderRequest();
            marketOrderRequest.setInstrument(instrumentName);
            marketOrderRequest.setUnits(units);
            orderCreateRequest.setOrder(marketOrderRequest);
            OrderCreateResponse orderCreateResponse = ctx.order.create(orderCreateRequest);
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
