package org.ediary.api.domain.openai.controller.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TranslateRequest {
    private String model;
    private List<Message> messages;

    public TranslateRequest(String model, String prompt) {
        this.model = model;
        this.messages =  new ArrayList<>();
        this.messages.add(new Message(
                "system",
                "Check the prompt sentence for translate it into korean.\"" +
                        "If prompt sentence is blank, give only \"작성된 일기가 없습니다.\" and quit reply." +
                        "If prompt sentence is Korean, give translate English."
        ));

        this.messages.add(new Message(
                "user",
                prompt));
    }
}
