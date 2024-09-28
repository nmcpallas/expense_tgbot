package com.nmcpallas.expense.rest;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FullExpenseInfoDto {

    private List<ExpenseInfoDto> expenses;
    private Integer sumOfExpectations;
    private Integer sumOfResults;

    public Integer getSumOfExpectations() {
        return expenses.stream().map(ExpenseInfoDto::getExpectation)
                .reduce(0, Integer::sum);
    }

    public Integer getSumOfResults() {
        return expenses.stream().map(ExpenseInfoDto::getResult)
                .reduce(0, Integer::sum);
    }
}
