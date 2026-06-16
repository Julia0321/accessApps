package com.example.accessapp.repository;

import com.example.accessapp.model.Role;
import com.example.accessapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.uuid = :uuid")
    Optional<User> findByUuid(@Param("uuid") String uuid);

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u JOIN FETCH u.roles r WHERE r = :role")
    List<User> findUserByRole(@Param("role") Role role);

    boolean existsByEmail(String email);
}
