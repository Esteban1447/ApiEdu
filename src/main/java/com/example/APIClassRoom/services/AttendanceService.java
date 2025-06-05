package com.example.APIClassRoom.services;

import com.example.APIClassRoom.helpers.AppiMSG;
import com.example.APIClassRoom.models.Attendance;
import com.example.APIClassRoom.repositories.IAttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    IAttendanceRepo repository;

    public Attendance saveAttendance(Attendance attendanceData) throws Exception {
        try {
            return this.repository.save(attendanceData);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Attendance modifyAttendance(Integer id, Attendance attendanceData) throws Exception {
        try {
            Optional<Attendance> searched = this.repository.findById(id);
            if (searched.isPresent()) {
                searched.get().setStudent(attendanceData.getStudent());
                searched.get().setCourse(attendanceData.getCourse());
                searched.get().setDate(attendanceData.getDate());
                searched.get().setStatus(attendanceData.getStatus());
                return this.repository.save(searched.get());
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_ATTENDANCE.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Attendance searchAttendanceById(Integer id) throws Exception {
        try {
            Optional<Attendance> searched = this.repository.findById(id);
            if (searched.isPresent()) {
                return searched.get();
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_ATTENDANCE.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public List<Attendance> searchAllAttendances() throws Exception {
        try {
            return this.repository.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public boolean deleteAttendance(Integer id) throws Exception {
        try {
            Optional<Attendance> searched = this.repository.findById(id);
            if (searched.isPresent()) {
                this.repository.deleteById(id);
                return true;
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_ATTENDANCE.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
