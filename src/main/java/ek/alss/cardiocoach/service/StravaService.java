package ek.alss.cardiocoach.service;

import ek.alss.cardiocoach.client.StravaClient;
import ek.alss.cardiocoach.models.CyclingActivity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StravaService {

    private final StravaClient stravaClient;

    public StravaService(StravaClient stravaClient) {
        this.stravaClient = stravaClient;
    }

    public Mono<List<CyclingActivity>> getActivities() {
        return stravaClient.getActivities();
    }
}
