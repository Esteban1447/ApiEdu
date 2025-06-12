package com.example.APIClassRoom.controllers;

import com.example.APIClassRoom.models.Grade;
import com.example.APIClassRoom.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService service;

    @GetMapping
    public ResponseEntity<?> getGrades(
            @RequestParam(required = false) Integer studentId,
            @RequestParam(required = false) Integer subjectId,
            @RequestParam(required = false) Integer subjectCourseId
    ) {
        try {
            if (studentId != null) {
                return ResponseEntity.ok(service.getGradesByStudentId(studentId));
            } else if (subjectId != null) {
                return ResponseEntity.ok(service.searchGradesBySubjectId(subjectId));
            } else if (subjectCourseId != null) {
                return ResponseEntity.ok(service.searchBySubjectCourseId(subjectCourseId));
            } else {
                return ResponseEntity.ok(service.searchAllGrades());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/student/{userId}")
    public ResponseEntity<?> searchByStudentUserId(@PathVariable Integer userId) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.searchByStudentUserId(userId));
        } catch (Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<?> searchByStudentAndSubject(
            @RequestParam Integer studentId,
            @RequestParam Integer subjectId
    ) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.searchByStudentAndSubject(studentId, subjectId));
        } catch (Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }

    @GetMapping("/by-subject")
    public ResponseEntity<?> searchBySubjectId(@RequestParam Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.service.searchGradesBySubjectId(id));
        } catch (Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Grade gradeData) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.service.saveGrade(gradeData));
        } catch (Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable Integer id, @RequestBody Grade data) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.service.modifyGrade(id, data));
        } catch (Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.service.searchGradeById(id));
        } catch (Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.service.deleteGrade(id));
        } catch (Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }
}
