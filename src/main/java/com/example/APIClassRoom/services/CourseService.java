package com.example.APIClassRoom.services;

import com.example.APIClassRoom.helpers.ApiMessage;
import com.example.APIClassRoom.models.Course;
import com.example.APIClassRoom.models.Teacher;
import com.example.APIClassRoom.repositories.ICourseRepo;
import com.example.APIClassRoom.repositories.ITeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    ICourseRepo repository;

    @Autowired
    ITeacherRepo teacherRepo;

    public List<Course> getCoursesByTeacherId(Integer teacherId) {
        return repository.findByTeacherId(teacherId);
    }

    public Course saveCourse(Course courseData) throws Exception {
        try {
            // Validar y asignar profesor si se pasa un ID
            if (courseData.getTeacher() != null && courseData.getTeacher().getId() != null) {
                Teacher teacher = teacherRepo.findById(courseData.getTeacher().getId())
                        .orElseThrow(() -> new Exception("Profesor no encontrado con id " + courseData.getTeacher().getId()));
                courseData.setTeacher(teacher);
            }

            return this.repository.save(courseData);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Course modifyCourse(Integer id, Course courseData) throws Exception {
        try {
            Optional<Course> searchedCourse = this.repository.findById(id);
            if (searchedCourse.isPresent()) {
                Course course = searchedCourse.get();

                // Actualizar nombre
                course.setName(courseData.getName());

                // Actualizar profesor si viene en el JSON
                if (courseData.getTeacher() != null && courseData.getTeacher().getId() != null) {
                    Teacher teacher = teacherRepo.findById(courseData.getTeacher().getId())
                            .orElseThrow(() -> new Exception("Profesor no encontrado con id " + courseData.getTeacher().getId()));
                    course.setTeacher(teacher);
                }

                return this.repository.save(course);
            } else {
                throw new Exception(ApiMessage.DONT_FOUND_COURSE.getTexto());
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
                throw new Exception(ApiMessage.DONT_FOUND_COURSE.getTexto());
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
                throw new Exception(ApiMessage.DONT_FOUND_COURSE.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
