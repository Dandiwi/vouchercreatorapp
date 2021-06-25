package com.example.vouchercreator.Service;

import com.example.vouchercreator.Model.Pakiet;
import com.example.vouchercreator.repository.PakietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class pakietyServiceImpl implements pakietyService {

    @Autowired PakietyRepository pakietyRepository;

    @Override
    public List<Pakiet> getAllPakiets() {return pakietyRepository.findAll();}

    @Override
    public Pakiet getPakietById(long id) {
        Optional<Pakiet> optional = pakietyRepository.findById(id);
        Pakiet pakiet = null;
        if(optional.isPresent()) {
            pakiet = optional.get();
        } else {
            throw new RuntimeException("Pakiet not found for id :: " + id);
        }
        return pakiet;
    }

    @Override
    public List<Pakiet> getAllPakietsNonNullValues() {
        return pakietyRepository.findAllPakietsNonNullValues();
    }





}
