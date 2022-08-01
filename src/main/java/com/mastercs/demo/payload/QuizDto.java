package com.mastercs.demo.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QuizDto {

    private String option;

    private Long id;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
