package com.example.APIClassRoom.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_teacher")
    private Integer id;

    private String speciality;

    @OneToMany(mappedBy = "teacher")
    @JsonManagedReference(value = "teacher-course")
    private List<Course> courses;

    @OneToOne()
    @JoinColumn(name = "fk_user", referencedColumnName = "id_user")
    @JsonManagedReference(value = "teacher-user")
    private User user;

    public Teacher() {}

    public Teacher(Integer id, String speciality) {
        this.id = id;
        this.speciality = speciality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @PreRemove
    private void unlinkCourses() {
        if (courses != null) {
            for (Course course : courses) {
                course.setTeacher(null);
            }
        }
    }
}
