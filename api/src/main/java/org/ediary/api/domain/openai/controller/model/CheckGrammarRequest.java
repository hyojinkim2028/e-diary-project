package org.ediary.api.domain.openai.controller.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CheckGrammarRequest {
    private String model;
    private List<Message> messages;

    public CheckGrammarRequest(String model, String prompt) {
        this.model = model;
        this.messages =  new ArrayList<>();
        this.messages.add(new Message(
                "system",
                "Check the prompt sentence for grammar errors ams please follow this format for your response\"" +
                        "corrected_sentence : If errors exist, give the corrected sentence. or else give ok\"" +
                        "korean : corrected_sentence in korean\"" +
                        "reason : give one detail reason to grammar errors(in korean and in a conversational tone)"));
        this.messages.add(new Message(
                "user",
                prompt));
    }
}