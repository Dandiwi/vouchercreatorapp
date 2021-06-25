package com.example.vouchercreator.repository;

import com.example.vouchercreator.Model.Pakiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PakietyRepository extends JpaRepository<Pakiet, Long> {

    @Query(value = "select * from pakiety", nativeQuery = true)
    List<Pakiet> findAllPakietsNonNullValues();
}