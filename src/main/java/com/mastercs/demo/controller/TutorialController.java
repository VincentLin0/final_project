package com.mastercs.demo.controller;

import com.mastercs.demo.bean.Tutorial;
import com.mastercs.demo.config.Result;
import com.mastercs.demo.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping(value = "/tutorial")
    public List<Tutorial> getAllTutorial()
    {
        List <Tutorial> tutorialList = tutorialRepository.findAll();
        return tutorialList;
    }
}
