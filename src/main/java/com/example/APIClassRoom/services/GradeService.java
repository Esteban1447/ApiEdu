package com.example.APIClassRoom.services;

import com.example.APIClassRoom.helpers.ApiMessage;
import com.example.APIClassRoom.models.Grade;
import com.example.APIClassRoom.repositories.IGradeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    IGradeRepo repository;

    public List<Grade> searchByStudentUserId(Integer userId) {
        return repository.findByStudentUserId(userId);
    }

    public List<Grade> searchGradesBySubjectId(Integer subjectId) {
        return repository.findBySubjectId(subjectId);
    }

    public List<Grade> searchByStudentAndSubject(Integer studentId, Integer subjectId) {
        return repository.findByStudentIdAndSubjectId(studentId, subjectId);
    }

    public List<Grade> searchBySubjectCourseId(Integer courseId) {
        return repository.findBySubjectCourseId(courseId);
    }

    public Grade saveGrade(Grade gradeData) throws Exception {
        try {
            return this.repository.save(gradeData);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Grade modifyGrade(Integer id, Grade gradeData) throws Exception {
        try {
            Optional<Grade> searched = this.repository.findById(id);
            if (searched.isPresent()) {
                searched.get().setStudent(gradeData.getStudent());
                searched.get().setSubject(gradeData.getSubject());
                searched.get().setScore(gradeData.getScore());
                searched.get().setEvaluationDate(gradeData.getEvaluationDate());
                return this.repository.save(searched.get());
            } else {
                throw new Exception(ApiMessage.DONT_FOUND_GRADE.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Grade searchGradeById(Integer id) throws Exception {
        try {
            Optional<Grade> searched = this.repository.findById(id);
            if (searched.isPresent()) {
                return searched.get();
            } else {
                throw new Exception(ApiMessage.DONT_FOUND_GRADE.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public List<Grade> searchAllGrades() throws Exception {
        try {
            return this.repository.findAllWithStudentAndUser(); // ← Nuevo método
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }


    public boolean deleteGrade(Integer id) throws Exception {
        try {
            Optional<Grade> searched = this.repository.findById(id);
            if (searched.isPresent()) {
                this.repository.deleteById(id);
                return true;
            } else {
                throw new Exception(ApiMessage.DONT_FOUND_GRADE.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

}