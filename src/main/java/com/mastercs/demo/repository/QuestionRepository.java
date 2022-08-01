package com.mastercs.demo.repository;

import com.mastercs.demo.bean.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    Question findQuestionByQuestionTitle(String questionTitle);

    Question findQuestionById(Long id);

    void deleteAllByQuestionTitle(String questionTitle);
}
