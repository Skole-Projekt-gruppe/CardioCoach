package ek.alss.cardiocoach.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OpenAiClient {
    private final WebClient client;

    public OpenAiClient(@Qualifier("chatGptWebClient") WebClient client){
        this.client = client;
    }

    public record SimpleResponse(String id, List<Output> output){}
    public record Output(String id, List<Content> content) {}
    public record Content(String text) {}

    public record SimpelRequest(String model, String input) {}


    public Mono<SimpleResponse> getResponses(String prompt){
        SimpelRequest body = new SimpelRequest("gpt-5-nano", prompt);
        return client.post()
                .uri("/responses")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .onStatus(s -> s.value() == 400, r -> r.bodyToMono(String.class)
                        .map( msg -> new IllegalArgumentException("OpenAI 400: " + msg)))
                .onStatus(s -> s.value() == 401, r -> r.bodyToMono(String.class)
                        .map(msg -> new IllegalArgumentException("OpenAI 401 Unauthorized: " + msg)))
                .onStatus(HttpStatusCode::isError, r -> r.bodyToMono(String.class)
                        .map(msg -> new IllegalArgumentException("OpenAI Error: " + msg)))
                .bodyToMono(SimpleResponse.class);
    }
}
