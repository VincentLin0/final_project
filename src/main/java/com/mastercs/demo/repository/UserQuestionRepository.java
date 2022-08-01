package com.mastercs.demo.repository;

import com.mastercs.demo.bean.EnumOption;
import com.mastercs.demo.bean.Question;
import com.mastercs.demo.bean.User;
import com.mastercs.demo.bean.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuestionRepository extends JpaRepository<UserQuestion,Long> {

    List<UserQuestion> findUserQuestionsByUserAndName(User user, EnumOption enumOption);
    List<UserQuestion> findUserQuestionsByUser(User user);

//    void deleteUserQuestionByUser(User user);

    void deleteAllByUser(User user);

    void deleteAllByQuestion(Question question);
}
