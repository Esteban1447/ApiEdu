package com.example.APIClassRoom.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grade")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    @JsonIgnoreProperties("grades") // Ignora la lista para evitar recursión
    private Student student;


    @ManyToOne
    @JoinColumn(name = "id_subject", nullable = true)
    private Subject subject;

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Column(nullable = true)
    private Double grade;

    @Column(name = "evaluation_date", nullable = true)
    private LocalDate evaluationDate;

    public Grade (){}

    public Grade (Integer id, Student student, Subject subject, Double score, LocalDate evaluationDate){
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.grade = score;
        this.evaluationDate = evaluationDate;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Double getScore() {
        return grade;
    }
    public void setScore(Double score) {
        this.grade = score;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }
    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }
}
