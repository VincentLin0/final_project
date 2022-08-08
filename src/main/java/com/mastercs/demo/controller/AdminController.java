package com.mastercs.demo.controller;

import com.mastercs.demo.bean.EnumOption;
import com.mastercs.demo.bean.Options;
import com.mastercs.demo.bean.Question;
import com.mastercs.demo.config.Result;
import com.mastercs.demo.payload.AdminAddDto;
import com.mastercs.demo.payload.AdminDeleteDto;
import com.mastercs.demo.payload.AllQuizResponse;
import com.mastercs.demo.repository.OptionRepository;
import com.mastercs.demo.repository.QuestionRepository;
import com.mastercs.demo.repository.UserQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
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
    public Result<?> adminAddQuestions(@Valid @RequestBody AdminAddDto adminQuizDto)
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

    @Transactional()
    @PostMapping("/delete-question")
    public Result<?> adminDeleteQuestions(@Valid @RequestBody AdminDeleteDto adminDeleteDto)
    {
        Question question = questionRepository.findQuestionByQuestionTitle(adminDeleteDto.getQuestion());
        if (question == null)
        {
            return Result.error("The question does not exist");
        }

        optionRepository.deleteOptionsByQuestion(question);
        questionRepository.deleteAllByQuestionTitle(adminDeleteDto.getQuestion());
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
