package com.example.APIClassRoom.repositories;

import com.example.APIClassRoom.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends JpaRepository <Teacher,Integer> {
}
