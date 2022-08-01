package com.mastercs.demo.payload;

import java.util.List;

public class QuizResponse {
    private String username;
    private String answer;
    private boolean isRight;


    public QuizResponse(String username, String answer, boolean isRight)
    {
        this.username = username;
        this.answer = answer;
        this.isRight = isRight;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean isRight) {
        this.isRight = isRight;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
