package ek.alss.cardiocoach.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class webClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    }

    // Til Data
    @Bean
    WebClient stravaWebClient(
            WebClient.Builder b,
            @Value("${strava.api.baseUrl}") String baseUrl
    ) {
        return b.clone()
                .baseUrl(baseUrl)
                .build();
    }


    // Til token refresh
    @Bean
    WebClient stravaAuthWebClient(
            WebClient.Builder b,
            @Value("${strava.api.authUrl}") String authUrl
    ) {
        return b.clone()
                .baseUrl(authUrl)
                .build();
    }
}
