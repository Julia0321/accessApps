package com.example.accessapp.repository;

import com.example.accessapp.model.Lock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockRepository extends JpaRepository<Lock, Long> {

}
