package com.katziio.app.repository.account;

import com.katziio.app.model.ExpenseActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseActivity,Long> {
}
