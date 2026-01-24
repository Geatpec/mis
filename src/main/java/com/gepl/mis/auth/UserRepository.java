package com.gepl.mis.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByRole(Role role);

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
