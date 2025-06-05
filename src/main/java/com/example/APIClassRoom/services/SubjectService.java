package com.example.APIClassRoom.services;

import com.example.APIClassRoom.helpers.AppiMSG;
import com.example.APIClassRoom.models.Subject;
import com.example.APIClassRoom.repositories.ISubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    ISubjectRepo repository;

    public Subject saveSubject(Subject subjectData) throws Exception {
        try {
            return this.repository.save(subjectData);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Subject modifySubject(Integer id, Subject subjectData) throws Exception {
        try {
            Optional<Subject> searchedSubject = this.repository.findById(id);
            if (searchedSubject.isPresent()) {
                searchedSubject.get().setName(subjectData.getName());
                searchedSubject.get().setCourse(subjectData.getCourse());
                return this.repository.save(searchedSubject.get());
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_SUBJECT.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Subject searchSubjectById(Integer id) throws Exception {
        try {
            Optional<Subject> searched = this.repository.findById(id);
            if (searched.isPresent()) {
                return searched.get();
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_SUBJECT.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public List<Subject> searchAllSubjects() throws Exception {
        try {
            return this.repository.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public boolean deleteSubject(Integer id) throws Exception {
        try {
            Optional<Subject> searched = this.repository.findById(id);
            if (searched.isPresent()) {
                this.repository.deleteById(id);
                return true;
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_SUBJECT.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
