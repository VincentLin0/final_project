package com.mastercs.demo.bean;

// 用于映射数据库到后端，数据库：baidu_search，表名：items，映射过来叫：Search

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "knowledge")//数据库表名
public class Search {
    @Id//定义主键，该注解下面的属性即为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int id;

    @Column(name="title")//定义表中的每一列，即每一个属性
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="kind")
    private String kind;

    @Column(name="pic1")
    private String pic1;

    @Column(name="pic2")
    private String pic2;

    @Column(name="pic3")
    private String pic3;

    @Column(name="audio")
    private String audio;

    @Column(name="video")
    private String video;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getKind() {
//        return kind;
//    }
//
//    public void setKind(String kind) {
//        this.kind = kind;
//    }
//
//    public String getPic1() {
//        return pic1;
//    }
//
//    public void setPic1(String pic1) {
//        this.pic1 = pic1;
//    }
//
//    public String getPic2() {
//        return pic2;
//    }
//
//    public void setPic2(String pic2) {
//        this.pic2 = pic2;
//    }
//
//    public String getPic3() {
//        return pic3;
//    }
//
//    public void setPic3(String pic3) {
//        this.pic3 = pic3;
//    }
//
//    public String getAudio() {
//        return audio;
//    }
//
//    public void setAudio(String audio) {
//        this.audio = audio;
//    }
//
//    public String getVideo() {
//        return video;
//    }
//
//    public void setVideo(String video) {
//        this.video = video;
//    }
}

