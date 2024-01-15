package com.katziio.app.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.katziio.app.util.enums.ExpenseCategory;
import lombok.Data;

import java.util.Date;

@Data
public class ExpenseRequestDTO {
    @JsonIgnore
    private Long id;
    private Date time;
    private Long amountSpent;
    private Long initialBalance;
    private Long updatedBalance;
    private ExpenseCategory category;
    private String spentOn;
}
