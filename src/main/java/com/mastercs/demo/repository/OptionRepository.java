package com.mastercs.demo.repository;

import com.mastercs.demo.bean.EnumOption;
import com.mastercs.demo.bean.EnumRole;
import com.mastercs.demo.bean.Options;
import com.mastercs.demo.bean.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Options, Long> {
    Options findOptionsByOptionTitleAndQuestion(String optionTitle, Question question);
    Options findOptionsByNameAndQuestion(EnumOption name, Question question);
    List<Options> findOptionsByQuestion(Question question);

    void deleteOptionsByQuestion(Question question);

}
