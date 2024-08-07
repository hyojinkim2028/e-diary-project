package org.ediary.api.domain.openai.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.ediary.api.domain.openai.controller.model.CheckGrammarRequest;
import org.ediary.api.domain.openai.controller.model.AIResponse;
import org.ediary.api.domain.openai.controller.model.ResponseContent;
import org.ediary.api.domain.openai.controller.model.TranslateRequest;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/check-grammar")
public class CheckGrammarController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    private final RestTemplate template;

    @GetMapping("")
    public ResponseContent checkGrammar(@RequestParam(name = "prompt")String prompt) throws JsonProcessingException, JSONException {
        ResponseContent responseContent = new ResponseContent();
        CheckGrammarRequest request = new CheckGrammarRequest(model,prompt);
        AIResponse chatGPTResponse =  template.postForObject(apiURL, request, AIResponse.class);

        String content =  chatGPTResponse.getChoices().get(0).getMessage().getContent();
        String correctSentence = extractCorrectSentence(content);
        String reason = extractReason(content);

        responseContent.setCorrect_sentence(correctSentence);
        responseContent.setReason(reason);
        return responseContent;
    }


    @GetMapping("/translation")
    public Object translationPrompt(@RequestParam(name = "prompt")String prompt) throws JsonProcessingException, JSONException {

        TranslateRequest request = new TranslateRequest(model,prompt);
        AIResponse chatGPTResponse =  template.postForObject(apiURL, request, AIResponse.class);

        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }


    // 문법 첨삭 response 포맷에 맞게 파싱
    private String extractCorrectSentence(String content) {
        int startIndex = content.indexOf("correct_sentence :") + "correct_sentence : ".length();
        int endIndex = content.indexOf("\n", startIndex);

        // 유효한 인덱스 검사
        if (startIndex < endIndex && endIndex > -1) {
            return content.substring(startIndex, endIndex).trim();
        } else {
            // 에러 처리 또는 기본값 설정
            return "Correct sentence not found";
        }
    }

    private String extractReason(String content) {
        int startIndex = content.indexOf("reason :") + "reason :".length();

        // 유효한 인덱스 검사
        if (startIndex < content.length()) {
            return content.substring(startIndex).trim();
        } else {
            // 에러 처리 또는 기본값 설정
            return "Reason not found";
        }
    }
}
