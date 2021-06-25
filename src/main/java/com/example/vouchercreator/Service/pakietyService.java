package com.example.vouchercreator.Service;

import com.example.vouchercreator.Model.Pakiet;

import java.util.List;

public interface pakietyService {
    List<Pakiet> getAllPakiets();

    Pakiet getPakietById(long id);

    List<Pakiet> getAllPakietsNonNullValues();
}
