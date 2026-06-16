package com.example.accessapp.repository;

import com.example.accessapp.model.Kontrakton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KontraktonRepository extends JpaRepository<Kontrakton, Long> {

}
