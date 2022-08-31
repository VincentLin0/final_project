package com.mastercs.demo.bean;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@Entity
@Table(name = "options")
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private EnumOption name;

    @NotBlank
    @Size(max = 100)
    @Column(name = "option_title")
    private String optionTitle;

    public Options() {

    }

//    public Options(EnumOption name) {
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Question getQuestion()
//    {
//        return question;
//    }
//
//    public void setQuestion(Question question) {
//        this.question = question;
//    }
//
//    public EnumOption getOption() {
//        return name;
//    }
//
//    public void setOption(EnumOption name) {
//        this.name = name;
//    }
//
//    public String getOptionTitle() {
//        return optionTitle;
//    }
//
//    public void setOptionTitle(String optionTitle) {
//        this.optionTitle = optionTitle;
//    }
}
