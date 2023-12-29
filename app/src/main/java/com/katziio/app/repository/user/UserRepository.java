package com.katziio.app.repository.user;

import com.katziio.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u where u.phone = :phone")
    Optional<User> findByPhone(@Param("phone") String phone);
}
