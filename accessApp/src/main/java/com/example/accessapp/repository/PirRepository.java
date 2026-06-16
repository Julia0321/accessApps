package com.example.accessapp.repository;

import com.example.accessapp.model.Pir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PirRepository extends JpaRepository<Pir, Long> {

    List<Pir> findAll();

    void deleteByPirId(Pir pir);

    @Query("SELECT p FROM Pir p WHERE p.pirId = :pirId ")
    Optional<Pir> findByPirId(@Param("pirId") Long pirId);
}
