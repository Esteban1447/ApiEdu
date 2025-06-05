package com.example.APIClassRoom.repositories;

import com.example.APIClassRoom.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttendanceRepo extends JpaRepository<Attendance, Integer> {
}
