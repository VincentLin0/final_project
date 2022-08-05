package com.mastercs.demo.controller;

import com.mastercs.demo.bean.EnumOption;
import com.mastercs.demo.bean.Options;
import com.mastercs.demo.bean.Question;
import com.mastercs.demo.config.Result;
import com.mastercs.demo.payload.AdminQuizDto;
import com.mastercs.demo.payload.AllQuizResponse;
import com.mastercs.demo.repository.OptionRepository;
import com.mastercs.demo.repository.QuestionRepository;
import com.mastercs.demo.repository.UserQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users/admin/quiz")
public class AdminController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    UserQuestionRepository userQuestionRepository;

    @GetMapping("")
    public String adminPage()
    {
        return "welcome to admin page!";
    }

    @PostMapping("/add-question")
    public Result<?> adminAddQuestions(@RequestBody AdminQuizDto adminQuizDto)
    {
        if (questionRepository.findQuestionByQuestionTitle(adminQuizDto.getQuestion())!= null)
        {
            return Result.error("Question already exists!");
        }

        Question question = new Question();
        question.setQuestionTitle(adminQuizDto.getQuestion());
        questionRepository.save(question);

        List<String> optionsList = adminQuizDto.getOptions();
        for (String option:optionsList)
        {
            Options options = new Options();
            options.setOptionTitle(option);
            options.setOption(EnumOption.OPTIONS_WRONG);
            options.setQuestion(question);
            optionRepository.save(options);
        }

        Options correctOption = optionRepository.findOptionsByOptionTitleAndQuestion(adminQuizDto.getAnswer(),question);
        correctOption.setOption(EnumOption.OPTIONS_CORRECT);
        optionRepository.save(correctOption);

        return Result.success("Added new question!");
    }

//    @PostMapping("/modify-questions")
//    public ResponseEntity adminModifyQuestions(@RequestBody ModifyQuizDto modifyQuizDto)
//    {
//        if (modifyQuizDto.getNewQuestion() == null)
//        {
//            return ResponseEntity.ok("Question remains same!");
//        }
//
//        Question question = questionRepository.findQuestionByQuestionTitle(modifyQuizDto.getOldQuestion());
//        question.setQuestionTitle(modifyQuizDto.getNewQuestion());
//        questionRepository.save(question);
//        return ResponseEntity.ok("Question modified successfully!");
//    }

    @Transactional()
    @PostMapping("/delete-question")
    public Result<?> adminDeleteQuestions(@RequestBody AdminQuizDto adminQuizDto)
    {
        Question question = questionRepository.findQuestionByQuestionTitle(adminQuizDto.getQuestion());
        if (question == null)
        {
            return Result.error("The question does not exist");
        }

        optionRepository.deleteOptionsByQuestion(question);
        questionRepository.deleteAllByQuestionTitle(adminQuizDto.getQuestion());
        if (userQuestionRepository != null)
        {
            userQuestionRepository.deleteAllByQuestion(question);
        }
        return Result.success("Deleted a question!");
    }

    @GetMapping("/all-quiz")
    public Result<?> showQuizList()
    {
        List<Question> questionList = questionRepository.findAll();

        if (questionList.size() == 0)
        {
            return Result.error("There is no question!");
        }

        List<AllQuizResponse> allQuizResponseList = new ArrayList<>();
        for (Question question : questionList)
        {
            List<Options> optionsList = optionRepository.findOptionsByQuestion(question);
            List<String> optionTitleList = new ArrayList<>();
            for (Options option : optionsList)
            {
                String optionTitle = option.getOptionTitle();
                optionTitleList.add(optionTitle);
            }
            Options answerOption= optionRepository.findOptionsByNameAndQuestion(EnumOption.OPTIONS_CORRECT,question);
            String answer = answerOption.getOptionTitle();
            Long id = question.getId();
            String questionTitle = question.getQuestionTitle();
            AllQuizResponse quizResponse = new AllQuizResponse(id, questionTitle, optionTitleList,answer);
            allQuizResponseList.add(quizResponse);
        }

        return Result.success(allQuizResponseList);
    }



}
