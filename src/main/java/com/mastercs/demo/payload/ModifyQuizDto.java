package com.mastercs.demo.payload;

import javax.validation.constraints.NotBlank;

public class ModifyQuizDto {

    @NotBlank
    private String oldQuestion;
    private String newQuestion;


    public String getNewQuestion() {
        return newQuestion;
    }

    public void setNewQuestion(String newQuestion) {
        this.newQuestion = newQuestion;
    }

    public String getOldQuestion() {
        return oldQuestion;
    }

    public void setOldQuestion(String oldQuestion) {
        this.oldQuestion = oldQuestion;
    }
}
