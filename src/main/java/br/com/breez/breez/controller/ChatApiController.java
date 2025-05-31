package br.com.breez.breez.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatApiController {
    private final ChatClient chatClient;

    @PostMapping("/ask")
    public String ask(@RequestBody String message) {
        UserMessage userMessage = new UserMessage(message);
        Prompt prompt = new Prompt(userMessage);
        return chatClient.prompt(prompt)
                .call()
                .content();
    }
}

