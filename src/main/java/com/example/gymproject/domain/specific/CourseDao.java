package com.example.gymproject.domain.specific;

import com.example.gymproject.domain.Course;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    List<Course> findCoursrByUserId(Map<String,Object> dataMap);

}
