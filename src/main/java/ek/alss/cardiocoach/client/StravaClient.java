package ek.alss.cardiocoach.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class StravaClient {

    private final WebClient stravaWebClient;
    private final WebClient stravaAuthWebClient;

    @Value("${strava.client-id}")
    private String clientId;

    @Value("${strava.client-secret}")
    private String clientSecret;

    @Value("${strava.access-token}")
    private String accessToken;

    @Value("${strava.refresh-token}")
    private String refreshToken;

    public StravaClient(
            @Qualifier("stravaWebClient") WebClient stravaWebClient,
            @Qualifier("stravaAuthWebClient") WebClient stravaAuthWebClient
    ) {
        this.stravaWebClient = stravaWebClient;
        this.stravaAuthWebClient = stravaAuthWebClient;
    }

    private record RefreshRequest(String client_id, String client_secret, String grant_type, String refresh_token) {
    }

    private record RefreshResponse(String token_type, String access_token, long expires_at, long expires_in,
                                   String refresh_token) {
    }

    public Mono<String> refreshToken() {
        return stravaAuthWebClient.post()
                .uri("/oauth/token")
                .bodyValue(new RefreshRequest(clientId, clientSecret, "refresh_token", refreshToken))
                .retrieve()
                .bodyToMono(RefreshResponse.class)
                .map(response -> {
                            this.accessToken = response.access_token;
                            this.refreshToken = response.refresh_token;
                            return accessToken;
                        }
                );
    }

    public Mono<String> getActivites() {
        return stravaWebClient.get()
                .uri("/athlete/activities?per_page=30&page=1")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientResponseException.Unauthorized.class, e -> {
                    return refreshToken().flatMap(token -> {
                        return stravaWebClient.get()
                                .uri("/athlete/activities?per_page=30&page=1")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                                .retrieve()
                                .bodyToMono(String.class);
                    });
                });
    }
}