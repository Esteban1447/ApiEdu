package com.example.APIClassRoom.services;

import com.example.APIClassRoom.helpers.AppiMSG;
import com.example.APIClassRoom.models.Student;
import com.example.APIClassRoom.repositories.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    IStudentRepo repository;

    public Student saveStudent(Student studentData)throws Exception{
        try{
            return this.repository.save(studentData);
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public Student modifyStudent(Integer id,Student studentData)throws Exception{
        try {
            Optional<Student> searchedStudent=this.repository.findById(id);

            if(searchedStudent.isPresent()){
                searchedStudent.get().setGrade(studentData.getGrade());
                return this.repository.save(searchedStudent.get());
            }else{
                throw new Exception(AppiMSG.DONT_FOUND_STUDENT.getTexto());
            }
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public Student searchStudentById(Integer id) throws Exception{
        try{
            Optional<Student> searchedStudentForMe=this.repository.findById(id);
            if(searchedStudentForMe.isPresent()){
                return searchedStudentForMe.get();
            }else{
                throw new Exception(AppiMSG.DONT_FOUND_STUDENT.getTexto());
            }
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public List<Student> searchAllStudents()throws Exception{
        try{
            return this.repository.findAll();
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public boolean deleteStudent(Integer id)throws Exception{
        try{
            Optional<Student> searchedStudent=this.repository.findById(id);
            if(searchedStudent.isPresent()){
                this.repository.deleteById(id);
                return true;
            }else{
                throw new Exception(AppiMSG.DONT_FOUND_STUDENT.getTexto());
            }

        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
