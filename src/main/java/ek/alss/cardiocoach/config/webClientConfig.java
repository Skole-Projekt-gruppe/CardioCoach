package ek.alss.cardiocoach.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class webClientConfig
{

    @Bean
    public WebClient.Builder webClientBuilder()
    {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    }

    @Bean
    public WebClient stravaClient(WebClient.Builder builder)
    {
        return builder.clone().baseUrl("https://www.strava.com/api/v3").build();
    }

    @Bean
    WebClient chatGptWebClient(
            WebClient.Builder b,
            @Value("OPENAI_API_KEY") String apiKey,
            @Value("https://api.openai.com/v1") String baseUrl
    )
    {
        if (apiKey == null || apiKey.isBlank())
        {
            throw new IllegalArgumentException("API-key must be provided");
        }
        if (baseUrl == null || baseUrl.isBlank())
        {
            throw new IllegalArgumentException("ChatGpt API base URL must be provided in properties");
        }

        return b.clone()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .build();
    }
}
