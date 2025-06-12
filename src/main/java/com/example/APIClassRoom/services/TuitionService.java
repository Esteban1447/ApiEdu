package com.example.APIClassRoom.services;

import com.example.APIClassRoom.helpers.ApiMessage;
import com.example.APIClassRoom.models.Tuition;
import com.example.APIClassRoom.repositories.ITution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TuitionService {
    @Autowired
    ITution repository;

    public Tuition saveInscription(Tuition tuitionData) throws Exception {
        try {
            return this.repository.save(tuitionData);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Tuition modifyInscription(Integer id, Tuition tuitionData) throws Exception {
        try {
            Optional<Tuition> searchedInscription = this.repository.findById(id);
            if (searchedInscription.isPresent()) {
                searchedInscription.get().setInscriptionDate(tuitionData.getInscriptionDate());
                return this.repository.save(searchedInscription.get());
            } else {
                throw new Exception(ApiMessage.DONT_FOUND_INSCRIPTION.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Tuition searchInscriptionById(Integer id) throws Exception {
        try {
            Optional<Tuition> searchedInscription = this.repository.findById(id);
            if (searchedInscription.isPresent()) {
                return searchedInscription.get();
            } else {
                throw new Exception(ApiMessage.DONT_FOUND_INSCRIPTION.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public List<Tuition> searchAllInscriptions() throws Exception {
        try {
            return this.repository.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public boolean deleteInscription(Integer id) throws Exception {
        try {
            Optional<Tuition> searchedInscription = this.repository.findById(id);
            if (searchedInscription.isPresent()) {
                this.repository.deleteById(id);
                return true;
            } else {
                throw new Exception(ApiMessage.DONT_FOUND_INSCRIPTION.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
