package com.mastercs.demo.controller;

import com.mastercs.demo.repository.OptionRepository;
import com.mastercs.demo.repository.QuestionRepository;
import com.mastercs.demo.repository.UserQuestionRepository;
import com.mastercs.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users/quiz")
//@PreAuthorize("hasRole('USER')")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository quizRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    UserQuestionRepository userQuestionRepository;

    @Autowired
    QuestionRepository questionRepository;

/*
    @PostMapping
    public ResponseEntity adminAccess(@RequestParam Long id, @RequestBody QuizDto quizRequest, @AuthenticationPrincipal SpringUser springUser) {
        Question question = questionRepository.findQuestionById(id);
        if(question == null)
        {
            return ResponseEntity.badRequest().body("Question does not exist!");
        }

        String optionName = quizRequest.getOption();
        Options option = optionRepository.findOptionsByOptionTitleAndQuestion(optionName,question);
        if (option == null)
        {
            return ResponseEntity.badRequest().body("Option does not exist!");
        }

        //add to UserQuestion table
        User user = userRepository.findUserByUsername(springUser.getUsername());
        UserQuestion userQuestion = new UserQuestion();
        userQuestion.setUser(user);
        userQuestion.setQuestion(question);
        userQuestion.setOptions(option);
        userQuestion.setName(option.getOption());
        userQuestionRepository.save(userQuestion);

        // get answer
        Options answer = optionRepository.findOptionsByNameAndQuestion(EnumOption.OPTIONS_CORRECT,question);
        String correctAnswer = answer.getOptionTitle();

        if (userQuestion.getName() == EnumOption.OPTIONS_WRONG)
        {
            return ResponseEntity.ok(new QuizResponse(springUser.getUsername(),correctAnswer,false));
        }
        return ResponseEntity.ok(new QuizResponse(springUser.getUsername(),correctAnswer,true));
    }

    @PostMapping("/question")
    public ResponseEntity getQuestion(@RequestBody QuizDto quizDto)
    {
        Long id = quizDto.getId();
        Question question = questionRepository.findQuestionById(id);
        if (question == null)
        {
            return ResponseEntity.badRequest().body("Question does not exist");
        }

        List<Options> options = optionRepository.findOptionsByQuestion(question);
        List<String> optionsList = new ArrayList<>();
        for (Options n : options)
        {
            optionsList.add(n.getOptionTitle());
        }

        return ResponseEntity.ok().body(new QuestionResponse(optionsList,question.getQuestionTitle()));
    }


    @GetMapping("/list-of-quiz")
    public ResponseEntity showQuizList()
    {
        List<Question> questionList = quizRepository.findAll();
        List<String> questionTitleList = new ArrayList<>();

        for (Question question:questionList)
        {
            questionTitleList.add(question.getQuestionTitle());
        }
        return ResponseEntity.ok(questionTitleList);
    }

    @Transactional
    @GetMapping("/result")
    public ResponseEntity getQuizResult(@AuthenticationPrincipal SpringUser springUser)
    {
        Long userid = springUser.getId();
        User user = userRepository.findUserById(userid);

        List<UserQuestion> allUserQuestionList = userQuestionRepository.findUserQuestionsByUser(user);
        List<UserQuestion> userQuestionList = userQuestionRepository.findUserQuestionsByUserAndName(user,EnumOption.OPTIONS_CORRECT);

        if (allUserQuestionList.size() == 0)
        {
            return ResponseEntity.badRequest().body("The user haven't take the quiz yet");
        }

        int allQuestion = allUserQuestionList.size();
        int correctQuestion = userQuestionList.size();

        int result = correctQuestion*100/allQuestion;
        userQuestionRepository.deleteAllByUser(user);

        return ResponseEntity.ok("You got " + result + " out of 100");
    }
*/

}
