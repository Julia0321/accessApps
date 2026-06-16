package com.example.accessapp.repository;

import com.example.accessapp.model.ReedSwitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReedSwitchRepository extends JpaRepository<ReedSwitch, Long> {

}
