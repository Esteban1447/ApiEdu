package com.example.APIClassRoom.controllers;

import com.example.APIClassRoom.models.Tuition;
import com.example.APIClassRoom.services.TuitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tuitions")
public class TuitionController {

    @Autowired
    TuitionService service;

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Tuition tuitionData) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.service.saveInscription(tuitionData));
        } catch(Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity modify(@PathVariable Integer id, @RequestBody Tuition data) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.service.modifyInscription(id, data));
        } catch(Exception errorAPI) {
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
                    .body(this.service.searchInscriptionById(id));
        } catch(Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> searchAll() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.service.searchAllInscriptions());
        } catch(Exception errorAPI) {
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
                    .body(this.service.deleteInscription(id));
        } catch(Exception errorAPI) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorAPI.getMessage());
        }
    }
}
