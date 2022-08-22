package com.mastercs.demo.bean;

import lombok.Data;

import javax.crypto.spec.OAEPParameterSpec;
import javax.persistence.*;
@Data
@Entity
@Table(name = "user_question")
public class UserQuestion {
//    @Id @Column(name="user_id") Long id;
//
//    @MapsId
//    @OneToOne(mappedBy = "userQuestion")
//    @JoinColumn(name = "user_id")   //same name as id @Column
//    private User user;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId")   //same name as id @Column
    private Question question;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "optionsId")   //same name as id @Column
    private Options options;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumOption name;

//    public UserQuestion()
//    {
//
//    }
//
//    public UserQuestion(EnumOption name)
//    {
//        this.name = name;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
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
//    public Question getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(Question question) {
//        this.question = question;
//    }
//
//    public Options getOptions() {
//        return options;
//    }
//
//    public void setOptions(Options options) {
//        this.options = options;
//    }
//
//    public EnumOption getName() {
//        return name;
//    }
//
//    public void setName(EnumOption name) {
//        this.name = name;
//    }
}
