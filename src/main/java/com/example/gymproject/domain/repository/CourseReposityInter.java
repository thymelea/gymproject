package com.example.gymproject.domain.repository;

import com.example.gymproject.domain.Course;
import com.example.gymproject.domain.specific.CourseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseReposityInter extends JpaRepository<Course,String>,CourseDao, JpaSpecificationExecutor<Course> {
}
