package com.katziio.app.repository.user;


import com.katziio.app.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OtpRepository extends JpaRepository<Otp,Long> {

    @Query("SELECT o FROM Otp o WHERE o.user.id = :id")
    Otp findByUserId(@Param("id") Long userId);
    @Query("SELECT o.otp_created_at FROM Otp o where o.user.id = :id")
    Date findOtpCreatedAtByUserId(@Param("id") Long userId);

    @Query("SELECT o.otp_generated FROM Otp o WHERE o.user.id = :userId ")
    String findLatestOtpByUserId(@Param("userId") Long userId);

    @Query("SELECT o.otp_attempts FROM Otp o where o.phone = :phone")
    Integer findNoOfOtpAttemptByUserId(@Param("phone") String phone);
}