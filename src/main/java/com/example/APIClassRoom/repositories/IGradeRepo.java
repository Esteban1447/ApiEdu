package com.example.APIClassRoom.repositories;

import com.example.APIClassRoom.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGradeRepo extends JpaRepository<Grade, Integer> {

    List<Grade> findBySubjectId(Integer subjectId);

    List<Grade> findByStudentIdAndSubjectId(Integer studentId, Integer subjectId);

    List<Grade> findByStudentUserId(Integer userId);

    List<Grade> findBySubjectCourseId(Integer courseId);

    @Query("SELECT g FROM Grade g JOIN FETCH g.student s JOIN FETCH s.user")
    List<Grade> findAllWithStudentAndUser();
}
