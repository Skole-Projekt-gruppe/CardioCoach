package ek.alss.cardiocoach.service;

import ek.alss.cardiocoach.client.StravaClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StravaService {

    private final StravaClient stravaClient;

    public StravaService(StravaClient stravaClient) {
        this.stravaClient = stravaClient;
    }

    public Mono<String> getActivites() {
        return stravaClient.getActivites();
    }
}
