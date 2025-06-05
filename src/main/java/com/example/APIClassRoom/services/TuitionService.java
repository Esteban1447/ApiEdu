package com.example.APIClassRoom.services;

import com.example.APIClassRoom.helpers.AppiMSG;
import com.example.APIClassRoom.models.Inscription;
import com.example.APIClassRoom.repositories.IInscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {
    @Autowired
    IInscriptionRepo repository;

    public Inscription saveInscription(Inscription inscriptionData) throws Exception {
        try {
            return this.repository.save(inscriptionData);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Inscription modifyInscription(Integer id, Inscription inscriptionData) throws Exception {
        try {
            Optional<Inscription> searchedInscription = this.repository.findById(id);
            if (searchedInscription.isPresent()) {
                searchedInscription.get().setInscriptionDate(inscriptionData.getInscriptionDate());
                return this.repository.save(searchedInscription.get());
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_INSCRIPTION.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public Inscription searchInscriptionById(Integer id) throws Exception {
        try {
            Optional<Inscription> searchedInscription = this.repository.findById(id);
            if (searchedInscription.isPresent()) {
                return searchedInscription.get();
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_INSCRIPTION.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public List<Inscription> searchAllInscriptions() throws Exception {
        try {
            return this.repository.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public boolean deleteInscription(Integer id) throws Exception {
        try {
            Optional<Inscription> searchedInscription = this.repository.findById(id);
            if (searchedInscription.isPresent()) {
                this.repository.deleteById(id);
                return true;
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_INSCRIPTION.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
