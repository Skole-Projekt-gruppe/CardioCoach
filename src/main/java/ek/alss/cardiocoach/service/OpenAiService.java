package ek.alss.cardiocoach.service;

import ek.alss.cardiocoach.client.OpenAiClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OpenAiService
{
    private final OpenAiClient openAiClient;

    public OpenAiService(OpenAiClient openAiClient){
        this.openAiClient = openAiClient;
    }

    public record ResponseDto (String response) {}

    public Mono<ResponseDto> getOpenAiResponse(String prompt){
        return openAiClient.getResponses(prompt)
                .map(this::mapToResponseDto);
    }

    private ResponseDto mapToResponseDto(OpenAiClient.SimpleResponse resp) {
        StringBuilder combinedText = new StringBuilder();
        for (OpenAiClient.Output output : resp.output()) {
            for (OpenAiClient.Content content : output.content()) {
                combinedText.append(content.text());
            }
        }
        return new ResponseDto(combinedText.toString());
    }
}
