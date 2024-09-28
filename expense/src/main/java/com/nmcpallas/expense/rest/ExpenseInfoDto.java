package com.nmcpallas.expense.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseInfoDto {

    private Category category;
    private Integer expectation;
    private Integer result;
}
