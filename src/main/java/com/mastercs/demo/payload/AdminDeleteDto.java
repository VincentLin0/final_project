package com.mastercs.demo.payload;

import javax.validation.constraints.NotBlank;

public class AdminDeleteDto {

    @NotBlank
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
