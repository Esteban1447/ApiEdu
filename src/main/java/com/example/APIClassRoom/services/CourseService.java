package com.example.APIClassRoom.services;

import com.example.APIClassRoom.helpers.AppiMSG;
import com.example.APIClassRoom.models.Course;
import com.example.APIClassRoom.repositories.ICourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    ICourseRepo repository;

    public Course saveCourse(Course courseData) throws Exception {
        try {
            return this.repository.save(courseData);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Course modifyCourse(Integer id, Course courseData) throws Exception {
        try {
            Optional<Course> searchedCourse = this.repository.findById(id);
            if (searchedCourse.isPresent()) {
                searchedCourse.get().setName(courseData.getName());
                return this.repository.save(searchedCourse.get());
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_COURSE.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Course searchCourseById(Integer id) throws Exception {
        try {
            Optional<Course> searchedCourse = this.repository.findById(id);
            if (searchedCourse.isPresent()) {
                return searchedCourse.get();
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_COURSE.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public List<Course> searchAllCourses() throws Exception {
        try {
            return this.repository.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public boolean deleteCourse(Integer id) throws Exception {
        try {
            Optional<Course> searchedCourse = this.repository.findById(id);
            if (searchedCourse.isPresent()) {
                this.repository.deleteById(id);
                return true;
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_COURSE.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}

