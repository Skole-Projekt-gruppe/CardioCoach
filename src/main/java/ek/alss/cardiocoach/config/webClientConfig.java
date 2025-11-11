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

    // OpenAI (ChatGPT)
    @Bean
    public WebClient chatGptWebClient(
            WebClient.Builder b,
            @Value("${OPENAI_API_KEY}") String apiKey,
            @Value("${OPENAI_BASE_URL:https://api.openai.com/v1}") String baseUrl
    ) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("API-key must be provided");
        }
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalArgumentException("ChatGPT API base URL must be provided");
        }

        return b.clone()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .build();
    }
}





