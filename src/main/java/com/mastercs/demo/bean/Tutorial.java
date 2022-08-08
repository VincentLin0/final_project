package com.mastercs.demo.bean;

import javax.persistence.*;

@Entity
@Table(name= "tutorial")//数据库表名
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")//定义表中的每一列，即每一个属性
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="video")
    private String video;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
