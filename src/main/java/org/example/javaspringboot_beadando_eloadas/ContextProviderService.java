package org.example.javaspringboot_beadando_eloadas;

import com.v20.Context;
import com.v20.ContextBuilder;
import org.springframework.stereotype.Service;

@Service
public class ContextProviderService {
    public static Context getContext(String applicationName) {
        try {
            return new ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication(applicationName).build();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
