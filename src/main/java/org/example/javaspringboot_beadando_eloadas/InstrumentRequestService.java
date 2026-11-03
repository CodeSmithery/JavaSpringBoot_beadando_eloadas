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
    private final Context ctx = ContextProviderService.getContext("ContextProviderService");
    public List<Instrument> getAllInstruments() {
        try {
            AccountInstrumentsRequest acRequest = new AccountInstrumentsRequest(Config.ACCOUNTID);
            AccountInstrumentsResponse acResponse = ctx.account.instruments(acRequest);
            return acResponse.getInstruments();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
