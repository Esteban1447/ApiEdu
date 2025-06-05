package com.example.APIClassRoom.repositories;

import com.example.APIClassRoom.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGradeRepo extends JpaRepository <Grade, Integer> {
}
