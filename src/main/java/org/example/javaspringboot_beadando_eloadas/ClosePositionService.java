package org.example.javaspringboot_beadando_eloadas;

import com.v20.Context;
import com.v20.trade.TradeCloseRequest;
import com.v20.trade.TradeSpecifier;
import org.springframework.stereotype.Service;

@Service
public class ClosePositionService {
    private final Context ctx = ContextProviderService.getContext("ClosePositionService");

    public void closePosition(String tradeId) {
        try {
            ctx.trade.close(new TradeCloseRequest(Config.ACCOUNTID, new TradeSpecifier(tradeId)));
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
