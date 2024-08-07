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
                        "If there is no error give only \"알맞게 잘 입력하셨습니다. \" and quit reply." +
                        "If errors exist, give the corrected sentence and reason please follow this format.(only reason is in Korean,informal speech)\n" +
                        "correct_sentence : give corrected sentence \n" +
                        "reason : give errors detail reason"
        ));

        this.messages.add(new Message(
                "user",
                prompt));
    }
}

