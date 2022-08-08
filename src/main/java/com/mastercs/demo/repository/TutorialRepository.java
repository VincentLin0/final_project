package com.mastercs.demo.repository;

import com.mastercs.demo.bean.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
