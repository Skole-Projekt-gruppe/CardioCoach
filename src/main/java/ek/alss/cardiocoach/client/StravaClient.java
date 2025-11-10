package ek.alss.cardiocoach.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StravaClient {

    private final WebClient stravaClient;

    public StravaClient(
            @Qualifier("stravaClient") WebClient stravaClient
    ) {
        this.stravaClient = stravaClient;
    }


}
