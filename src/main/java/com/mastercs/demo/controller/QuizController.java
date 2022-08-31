package com.mastercs.demo.controller;

import com.mastercs.demo.bean.*;
import com.mastercs.demo.bean.Result;
import com.mastercs.demo.payload.QuestionResponse;
import com.mastercs.demo.payload.QuizDto;
import com.mastercs.demo.payload.QuizResponse;
import com.mastercs.demo.repository.OptionRepository;
import com.mastercs.demo.repository.QuestionRepository;
import com.mastercs.demo.repository.UserQuestionRepository;
import com.mastercs.demo.repository.UserRepository;
import com.mastercs.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users/quiz")
public class QuizController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    UserQuestionRepository userQuestionRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Value("${file-path}")
    private String filePath;


    //user answer questions
    @PostMapping
    public Result<?> userAccess(@RequestParam Long id, @RequestBody QuizDto quizRequest, HttpServletRequest request) {
        Question question = questionRepository.findQuestionById(id);
        if(question == null)
        {
            return Result.error("Question does not exist!");
        }

        String optionName = quizRequest.getOption();
        Options option = optionRepository.findOptionsByOptionTitleAndQuestion(optionName,question);
        if (option == null)
        {
            return Result.error("Option does not exist!");
        }

        String token = request.getHeader("token");
        String username = JwtUtils.getCurrentUsername(token);

        //add to UserQuestion table
        User user = userRepository.findUserByUsername(username);
        UserQuestion userQuestion = new UserQuestion();
        saveToUserQuestion(userQuestion,user,question,option);

        // get answer
        Options answer = optionRepository.findOptionsByNameAndQuestion(EnumOption.OPTIONS_CORRECT,question);
        String correctAnswer = answer.getOptionTitle();

        if (userQuestion.getName() == EnumOption.OPTIONS_WRONG)
        {
            return Result.success(new QuizResponse(username,correctAnswer,false));
        }
        return Result.success(new QuizResponse(username,correctAnswer,true));
    }


    //get question by id
    @PostMapping("/question")
    public Result<?> getQuestion(@RequestBody QuizDto quizDto)
    {
        Long id = quizDto.getId();
        Question question = questionRepository.findQuestionById(id);
        if (question == null)
        {
            return Result.error("Question does not exist");
        }

        List<Options> options = optionRepository.findOptionsByQuestion(question);
        List<String> optionsList = new ArrayList<>();
        for (Options n : options)
        {
            optionsList.add(n.getOptionTitle());
        }

        return Result.success(new QuestionResponse(optionsList,modifyPath(question)));
    }


    //get all question
    @GetMapping("/list-of-quiz")
    public Result<?> showQuizList()
    {
        List<Question> questionList = questionRepository.findAll();
        List<Long> questionIdList = new ArrayList<>();

        for (Question question:questionList)
        {
            questionIdList.add(question.getId());
        }
        return Result.success(questionIdList);
    }

    @Transactional
    @GetMapping("/result")
    public Result<?> getQuizResult(HttpServletRequest request)
    {
        String token = request.getHeader("token");
        String username = JwtUtils.getCurrentUsername(token);
        User user = userRepository.findUserByUsername(username);

        List<UserQuestion> allUserQuestionList = userQuestionRepository.findUserQuestionsByUser(user);
        List<UserQuestion> userQuestionList = userQuestionRepository.findUserQuestionsByUserAndName(user,EnumOption.OPTIONS_CORRECT);

        if (allUserQuestionList.size() == 0)
        {
            return Result.error("The user hasn't take the quiz yet");
        }

        int allQuestion = allUserQuestionList.size();
        int correctQuestion = userQuestionList.size();

        int result = correctQuestion*100/allQuestion;
        userQuestionRepository.deleteAllByUser(user);

        return Result.success("You got " + result + " out of 100");
    }

    @Transactional
    @PostMapping("/clear-data")
    public Result<?> clearUserData(HttpServletRequest request)
    {
        String token = request.getHeader("token");
        String username = JwtUtils.getCurrentUsername(token);
        User user = userRepository.findUserByUsername(username);

        if (userQuestionRepository == null)
        {
            return Result.error("There is no user_question data to delete");
        }
        userQuestionRepository.deleteAllByUser(user);
        return Result.success("Deleted user_question data successfully!");
    }

    private Question modifyPath(Question question)
    {
        if (question.getImage() != null)
        {
            question.setImage(filePath+question.getImage());
        }
        if (question.getAudio() != null)
        {
            question.setAudio(filePath+question.getAudio());
        }
        return question;
    }

    private void saveToUserQuestion(UserQuestion userQuestion,User user, Question question,Options option)
    {
        userQuestion.setUser(user);
        userQuestion.setQuestion(question);
        userQuestion.setOptions(option);
        userQuestion.setName(option.getName());
        userQuestionRepository.save(userQuestion);
    }

}
