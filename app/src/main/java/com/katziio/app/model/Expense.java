package com.katziio.app.model;

import com.katziio.app.dto.request.ExpenseDTO;
import com.katziio.app.util.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "account_activity")
@NoArgsConstructor
public class Expense {
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
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Expense(ExpenseDTO expenseDTO)
    {
       this.id = expenseDTO.getId() != null? expenseDTO.getId() : null;
       this.amountSpent = expenseDTO.getAmountSpent();
       this.category = expenseDTO.getCategory();
       this.spentOn = expenseDTO.getSpentOn();
       this.initialBalance = expenseDTO.getInitialBalance();
       this.updatedBalance = expenseDTO.getUpdatedBalance();
       this.spentOn = expenseDTO.getSpentOn();
    }
}
