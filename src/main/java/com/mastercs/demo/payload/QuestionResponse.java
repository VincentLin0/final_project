package com.mastercs.demo.payload;

import java.util.List;

public class QuestionResponse {

    private List<String> options;
    private String question;

    public QuestionResponse(List<String> options, String question)
    {
        this.options = options;
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
