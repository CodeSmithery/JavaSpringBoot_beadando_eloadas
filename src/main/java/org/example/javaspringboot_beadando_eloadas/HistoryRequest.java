package org.example.javaspringboot_beadando_eloadas;


import com.v20.Context;
import com.v20.ContextBuilder;
import org.springframework.stereotype.Service;
import com.v20.primitives.InstrumentName;

import java.util.List;

import com.v20.instrument.CandlestickGranularity;
import com.v20.instrument.InstrumentCandlesRequest;
import com.v20.instrument.InstrumentCandlesResponse;
import com.v20.instrument.Candlestick;


@Service
public class HistoryRequest {
    public List<Candlestick> getCandlesticks(InstrumentName instrumentName, CandlestickGranularity candlestickGranularity)  {
        try {
            Context ctx = new ContextBuilder(Config.URL)
                    .setToken(Config.TOKEN)
                    .setApplication("HistoryRequest")
                    .build();

            InstrumentCandlesRequest instCanRequest = new InstrumentCandlesRequest(instrumentName);
            instCanRequest.setGranularity(candlestickGranularity);
            instCanRequest.setCount(10L);

            InstrumentCandlesResponse instCanResponse = ctx.instrument.candles(instCanRequest);

            return instCanResponse.getCandles();
        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
