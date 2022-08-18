package com.portfolio.lucasfranco.authentication.service;

import com.portfolio.lucasfranco.authentication.entity.UserEntity;
import com.portfolio.lucasfranco.authentication.repository.UserRepository;
import com.portfolio.lucasfranco.exceptions.ParamBadRequest;
import com.portfolio.lucasfranco.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonService personService;


    public Optional<UserEntity> getByNameUser(String nameUser){
        return userRepository.findByNameUser(nameUser);
    }

    public boolean existsByNameUser(String nameUser){
        return userRepository.existsByNameUser(nameUser);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(UserEntity user){
        validations(user);
        userRepository.save(user);
    }

    private void validations(UserEntity user) {
        if(userRepository.existsByNameUser(user.getNameUser())){
            throw new ParamBadRequest("Ya existe ese usuario");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ParamBadRequest("Ya existe ese mail");
        }
    }
    /*
    @Override
    public UserEntity saveUser(UserDTO userDTO) {

        UserEntity user= new UserEntity();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        PersonEntity person= personService.findPersonById(PERSON_ID);

        return userRepository.save(user);

    }

     */
}
