package com.example.APIClassRoom.repositories;

import com.example.APIClassRoom.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStudentRepo extends JpaRepository <Student, Integer> {
    Optional<Student> findByUserId(Integer userId);
}
