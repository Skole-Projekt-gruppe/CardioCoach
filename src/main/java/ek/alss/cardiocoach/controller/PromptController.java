package ek.alss.cardiocoach.controller;

import ek.alss.cardiocoach.service.OpenAiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/prompt")
public class PromptController {

    private final OpenAiService openAiService;

    public PromptController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    public record QueryRequest(String prompt) {}

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Mono<ResponseEntity<OpenAiService.ResponseDto>> ask(@RequestBody QueryRequest query) {
        return openAiService
                .getOpenAiResponse(query.prompt())
                .map(ResponseEntity::ok);
    }
}
