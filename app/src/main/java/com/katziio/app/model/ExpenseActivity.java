package com.katziio.app.model;

import com.katziio.app.util.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "account_activity")
public class ExpenseActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @Column(name = "activity_id")
    private Long id;
    private Date time;
    private Long amountSpent;
    private Long initialBalance;
    private Long updatedBalance;
    private ExpenseCategory category;
    private String spentOn;
}
