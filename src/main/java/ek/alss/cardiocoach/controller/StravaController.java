package ek.alss.cardiocoach.controller;

import ek.alss.cardiocoach.models.CyclingActivity;
import ek.alss.cardiocoach.service.StravaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/strava")
public class StravaController {

    private final StravaService stravaService;

    public StravaController(StravaService stravaService) {
        this.stravaService = stravaService;
    }

    @GetMapping("/activities")
    public Mono<List<CyclingActivity>> getActivities() {
        return stravaService.getActivities();
    }
}
