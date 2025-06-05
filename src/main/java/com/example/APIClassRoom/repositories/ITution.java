package com.example.APIClassRoom.repositories;

import com.example.APIClassRoom.models.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInscriptionRepo extends JpaRepository <Inscription, Integer> {
}
