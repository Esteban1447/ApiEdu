package com.example.APIClassRoom.controllers;

import com.example.APIClassRoom.models.Course;
import com.example.APIClassRoom.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Course courseData) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.saveCourse(courseData));
        } catch (Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable Integer id, @RequestBody Course data) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.modifyCourse(id, data));
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
                    .body(service.searchCourseById(id));
        } catch (Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> searchAll(@RequestParam(required = false) Integer teacherId) {
        try {
            if (teacherId != null) {
                return ResponseEntity.ok(service.getCoursesByTeacherId(teacherId));
            } else {
                return ResponseEntity.ok(service.searchAllCourses());
            }
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
                    .body(service.deleteCourse(id));
        } catch (Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }
}
