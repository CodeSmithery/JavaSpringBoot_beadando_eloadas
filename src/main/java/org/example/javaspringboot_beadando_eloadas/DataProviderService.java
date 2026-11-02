package org.example.javaspringboot_beadando_eloadas;

import com.v20.pricing.ClientPrice;
import com.v20.primitives.Instrument;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataProviderService {
    public List<Instrument> instruments = new ArrayList<>();
    public List<ClientPrice> clientPrices = new ArrayList<>();

    private final PriceRequestService priceRequest;
    private final InstrumentRequestService instrumentRequest;

    public List<ClientPrice> getClientPrices() { return clientPrices;}
    public List<Instrument> getInstruments() {return instruments;}

    public DataProviderService(PriceRequestService priceRequest, InstrumentRequestService instrumentRequest) {
        this.priceRequest = priceRequest;
        this.instrumentRequest = instrumentRequest;
    }

    @Scheduled(initialDelay = 0, fixedRate = 30000)
    private void refresh() {
        System.out.println("Refreshing price...");
        instruments = instrumentRequest.getAllInstruments();
        clientPrices = priceRequest.getAllClientPrices(instruments.stream().map(Instrument::getName).toList());
    }
}
