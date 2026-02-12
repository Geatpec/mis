package com.gepl.mis.auth;

import com.gepl.mis.auth.dto.UserTableResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByRole(Role role);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    // ✅ Dashboard – Users table
    @Query("""
        SELECT new com.gepl.mis.auth.dto.UserTableResponse(
            u.id,
            u.username,
            u.role,
            u.createdAt,
            u.updatedAt
        )
        FROM User u
        ORDER BY u.createdAt DESC
    """)
    Page<UserTableResponse> fetchUsersForDashboard(Pageable pageable);
}
