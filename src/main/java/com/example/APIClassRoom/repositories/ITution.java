package com.example.APIClassRoom.repositories;

import com.example.APIClassRoom.models.Tuition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITution extends JpaRepository <Tuition, Integer> {
}
