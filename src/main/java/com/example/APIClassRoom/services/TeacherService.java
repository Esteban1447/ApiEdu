package com.example.APIClassRoom.services;

import com.example.APIClassRoom.helpers.AppiMSG;
import com.example.APIClassRoom.models.Teacher;
import com.example.APIClassRoom.repositories.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    ITeacherRepository repository;

    public Teacher saveTeacher(Teacher teacherData)throws Exception{
        try{
            return this.repository.save(teacherData);
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public Teacher modifyTeacher(Integer id,Teacher teacherData)throws Exception{
        try {
            Optional<Teacher> searchedTeacher=this.repository.findById(id);

            if(searchedTeacher.isPresent()){
                searchedTeacher.get().setSpeciality(teacherData.getSpeciality());//Modificando un dato viejo
                return this.repository.save(searchedTeacher.get());
            }else{
                throw new Exception(AppiMSG.DONT_FOUND_TEACHER.getTexto());
            }
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public Teacher searchTeacherById(Integer id) throws Exception{
        try{
            Optional<Teacher> searchedTeacherForMe=this.repository.findById(id);
            if(searchedTeacherForMe.isPresent()){
                return searchedTeacherForMe.get();
            }else{
                throw new Exception(AppiMSG.DONT_FOUND_TEACHER.getTexto());
            }
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public List<Teacher> searchAllTeachers()throws Exception{
        try{
            return this.repository.findAll();
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public boolean deleteTeacher(Integer id)throws Exception{
        try{
            Optional<Teacher> searchedTeacher=this.repository.findById(id);
            if(searchedTeacher.isPresent()){
                this.repository.deleteById(id);
                return true;
            }else{
                throw new Exception(AppiMSG.DONT_FOUND_TEACHER.getTexto());
            }

        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
