package com.mastercs.demo.payload;

import com.mastercs.demo.bean.Options;

import javax.xml.transform.sax.SAXResult;
import java.util.List;

public class AllQuizResponse {
    private Long questionId;
    private String question;
    private List<String> options;
    private String answer;

    public AllQuizResponse(Long questionId, String question, List<String> options, String answer)
    {
        this.questionId = questionId;
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String > getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
