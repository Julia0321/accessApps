package com.example.accessapp.repository;

import com.example.accessapp.model.AccessData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessDataRepository extends JpaRepository<AccessData, Integer> {
}
