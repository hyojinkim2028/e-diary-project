package org.ediary.api.domain.openai.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckGrammarContent {
    private String correct_content;
    private String reason;
}