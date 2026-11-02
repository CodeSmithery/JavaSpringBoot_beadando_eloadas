package org.example.javaspringboot_beadando_eloadas;

import com.v20.Context;
import com.v20.ContextBuilder;
import com.v20.account.AccountInstrumentsRequest;
import com.v20.account.AccountInstrumentsResponse;
import com.v20.primitives.Instrument;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstrumentRequestService {
    public List<Instrument> getAllInstruments() {
        try {
            Context ctx = new ContextBuilder(Config.URL)
                    .setToken(Config.TOKEN)
                    .setApplication("InstrumentRequestService")
                    .build();

            AccountInstrumentsRequest acRequest = new AccountInstrumentsRequest(Config.ACCOUNTID);

            AccountInstrumentsResponse acResponse = ctx.account.instruments(acRequest);

            return acResponse.getInstruments();

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
