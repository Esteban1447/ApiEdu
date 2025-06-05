package com.example.APIClassRoom.repositories;

import com.example.APIClassRoom.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ISubjectRepo extends JpaRepository <Subject, Integer> {
}
