package com.example.accessapp.repository;

import com.example.accessapp.enums.EnumRole;
import com.example.accessapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT r FROM Role r JOIN FETCH r.roleUser WHERE r.uuid = :uuid")
    Optional<Role> findByUuid(@Param("uuid") String uuid);

    @Query("SELECT r FROM Role r WHERE r.type = :type")
    Optional<Role> findByRoleName(@Param("type") EnumRole type);
}
