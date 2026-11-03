package org.example.javaspringboot_beadando_eloadas;

import com.v20.Context;
import com.v20.ContextBuilder;
import com.v20.trade.Trade;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListOpenPositionsService {
    private final Context ctx = ContextProviderService.getContext("ListOpenPositionsService");

    public List<Trade>  listOpenPositions() {
        return null;
    }
}
