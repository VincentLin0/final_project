package com.mastercs.demo.payload;

import com.mastercs.demo.bean.Question;

import java.util.List;

public class QuestionResponse {

    private List<String> options;
    //private String question;
    private Question question;

    public QuestionResponse(List<String> options, Question question)
    {
        this.options = options;
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
