package com.example.gymproject.service;

import com.example.gymproject.domain.Course;
import com.example.gymproject.domain.repository.CourseReposityInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("courseService")
public class CourseService {
    @Autowired
    private CourseReposityInter courseReposityInter;

    public List<Course> courseList(){
       return courseReposityInter.findAll();
    }
    public List<Course>getCourseList(Map<String,Object> dataMap){
        return courseReposityInter.findCoursrByUserId(dataMap);
    }
    @Transactional
    public void save(Course course){
        courseReposityInter.save(course);
    }
}
