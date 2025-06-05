package com.example.APIClassRoom.services;

import com.example.APIClassRoom.helpers.AppiMSG;
import com.example.APIClassRoom.models.User;
import com.example.APIClassRoom.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository repository;

    public User saveUser(User userData) throws Exception {
        try {
            return this.repository.save(userData);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public User modifyUser(Integer id, User userData) throws Exception {
        try {
            Optional<User> searchedUser = this.repository.findById(id);
            if (searchedUser.isPresent()) {
                searchedUser.get().setName(userData.getName());
                searchedUser.get().setEmail(userData.getEmail());
                searchedUser.get().setPassword(userData.getPassword());
                searchedUser.get().setPhone(userData.getPhone());
                searchedUser.get().setUserType(userData.getUserType());
                return this.repository.save(searchedUser.get());
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_USER.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public User searchUserById(Integer id) throws Exception {
        try {
            Optional<User> searchedUser = this.repository.findById(id);
            if (searchedUser.isPresent()) {
                return searchedUser.get();
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_USER.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public List<User> searchAllUsers() throws Exception {
        try {
            return this.repository.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    public boolean deleteUser(Integer id) throws Exception {
        try {
            Optional<User> searchedUser = this.repository.findById(id);
            if (searchedUser.isPresent()) {
                this.repository.deleteById(id);
                return true;
            } else {
                throw new Exception(AppiMSG.DONT_FOUND_USER.getTexto());
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
