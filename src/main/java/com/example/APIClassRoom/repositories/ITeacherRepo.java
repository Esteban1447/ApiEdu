package com.example.APIClassRoom.repositories;

import com.example.APIClassRoom.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITeacherRepo extends JpaRepository <Teacher,Integer> {
    List<Teacher> findByCoursesId(Integer courseId);
    Optional<Teacher> findByUserId(Integer userId);
}
