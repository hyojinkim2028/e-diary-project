package org.ediary.api.domain.openai.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ediary.api.domain.openai.controller.model.CheckGrammarRequest;
import org.ediary.api.domain.openai.controller.model.CheckGrammarResponse;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/check-grammar")
public class CheckGrammarController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("")
    public Object checkGrammar(@RequestParam(name = "prompt")String prompt) throws JsonProcessingException, JSONException {

        CheckGrammarRequest request = new CheckGrammarRequest(model,prompt);
        CheckGrammarResponse chatGPTResponse =  template.postForObject(apiURL, request, CheckGrammarResponse.class);

        String content = chatGPTResponse.getChoices().get(0).getMessage().getContent();
        System.out.println(content);

        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }
}
